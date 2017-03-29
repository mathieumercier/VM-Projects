package fr.umlv.smalljs.rt;

import static java.util.Objects.requireNonNull;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class JSObject {
	private final JSObject proto;
	private HiddenClass hiddenClass;
	private Object[] values;
	private final String name;
	private final MethodHandle mh;

	/**
	 * The undefined object
	 */
	public static final Object UNDEFINED = new Object() {
		@Override
		public String toString() {
			return "undefined";
		}
	};

	private static final MethodHandle GENERIC_INVOKER, BINOP_INVOKER, FAIL_INVOKER;
	static {
		Lookup lookup = MethodHandles.lookup();
		try {
			GENERIC_INVOKER = lookup.findVirtual(GenericInvoker.class, "invoke",
					MethodType.methodType(Object.class, Object[].class));
			BINOP_INVOKER = lookup.findVirtual(BinOpInvoker.class, "invoke",
					MethodType.methodType(Object.class, Object.class, Object.class, Object.class));
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw new AssertionError(e);
		}
		FAIL_INVOKER = GENERIC_INVOKER.bindTo((GenericInvoker) __ -> {
			throw new Failure("this value can not be applied");
		}).asVarargsCollector(Object[].class);
	}

	/**
	 * Object[] -> Object
	 */
	public interface GenericInvoker {
		Object invoke(Object[] arg);
	}

	/**
	 * Object Object Object -> Object
	 */
	public interface BinOpInvoker {
		Object invoke(Object receiver, Object arg1, Object arg2);
	}

	/**
	 * Create JSObject with a proto, a name and a method handle Created object
	 * has HiddenClass ROOT
	 * 
	 * @param proto
	 * @param name
	 * @param mh
	 */
	private JSObject(JSObject proto, String name, MethodHandle mh) {
		this.proto = proto;
		this.hiddenClass = HiddenClass.ROOT;
		this.values = NO_VALUES;

		this.name = requireNonNull(name);
		this.mh = requireNonNull(mh);
	}

	/**
	 * Create new object with given proto (name is object, methodhandle is
	 * FAIL_INVOKER)
	 * 
	 * @param proto
	 * @return
	 */
	public static JSObject newObject(JSObject proto) {
		return new JSObject(proto, "object", FAIL_INVOKER);
	}

	/**
	 * Create new environment with given parent (name is env, mh is
	 * FAIL_INVOKER)
	 * 
	 * @param parent
	 * @return
	 */
	public static JSObject newEnv(JSObject parent) {
		return new JSObject(parent, "env", FAIL_INVOKER);
	}

	/**
	 * Create new function with given name and method handle Has a member
	 * "apply", which is itself
	 * 
	 * @param name
	 * @param mh
	 * @return
	 */
	public static JSObject newFunction(String name, MethodHandle mh) {
		JSObject function = new JSObject(null, "function " + name, mh);
		function.register("apply", function);
		return function;
	}

	public static JSObject newFunction(String name, BinOpInvoker invoker) {
		return newFunction(name, BINOP_INVOKER.bindTo(invoker));
	}

	public static JSObject newFunction(String name, GenericInvoker invoker) {
		return newFunction(name, GENERIC_INVOKER.bindTo(invoker).asVarargsCollector(Object[].class));
	}

	public MethodHandle getMethodHandle() {
		return mh;
	}

	public Object invoke(Object... args) {
		if (!mh.isVarargsCollector() && args.length != mh.type().parameterCount()) {
			throw new Failure("arguments doesn't match parameters count " + (args.length - 1) + " "
					+ (mh.type().parameterCount() - 1));
		}
		try {
			return mh.invokeWithArguments(args);
		} catch (Throwable e) {
			if (e instanceof RuntimeException) {
				throw (RuntimeException) e;
			}
			if (e instanceof Error) {
				throw (Error) e;
			}
			throw new Failure(e.getMessage(), e);
		}
	}

	private static final Object[] NO_VALUES = new Object[0];

	public static class HiddenClass {
		/**
		 * First ever HiddenClass
		 */
		static final HiddenClass ROOT = new HiddenClass();

		/**
		 * Associate field name with slot index
		 */
		final LinkedHashMap<String, Integer> slotMap = new LinkedHashMap<>();
		// Associate subclasses?
		// They seem to have all fields of this, plus one field
		private final HashMap<String, HiddenClass> forwardMap = new HashMap<>();

		HiddenClass() {
			// package private
		}

		/**
		 * Slot index of a field name
		 * 
		 * @param key
		 *            the field name
		 * @return the slot index or -1 if no such field
		 */
		public int slot(String key) {
			return slotMap.getOrDefault(key, -1);
		}

		/**
		 * Forward class by one field
		 * 
		 * @param key
		 *            the field to add
		 * @return A HiddenClass that has the same fields as this, + the new
		 *         field
		 */
		HiddenClass forward(String key) {
			return forwardMap.computeIfAbsent(key, k -> {
				HiddenClass hiddenClass = new HiddenClass();
				hiddenClass.slotMap.putAll(slotMap);
				hiddenClass.slotMap.put(key, hiddenClass.slotMap.size());
				return hiddenClass;
			});
		}
	}

	public HiddenClass getHiddenClass() {
		return hiddenClass;
	}

	public Object[] getValueArray() {
		return values;
	}

	public Object lookup(String key) {
		requireNonNull(key);
		for (JSObject current = this;; current = current.proto) {
			int slot = current.hiddenClass.slot(key);
			if (slot != -1) {
				return current.values[slot];
			}
			if (current.proto == null) {
				return UNDEFINED;
			}
		}
	}

	public void register(String key, Object value) {
		requireNonNull(key);
		requireNonNull(value);
		int slot = hiddenClass.slot(key);
		if (slot == -1) {
			hiddenClass = hiddenClass.forward(key);
			slot = hiddenClass.slot(key);
			values = Arrays.copyOf(values, slot + 1);
		}
		values[slot] = value;
	}

	public int length() {
		return values.length;
	}

	public Set<String> keys() {
		return hiddenClass.slotMap.keySet();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(this, builder, Collections.newSetFromMap(new IdentityHashMap<>()));
		return builder.toString();
	}

	public String toStringPretty() {
		StringBuilder builder = new StringBuilder();
		toStringPretty(this, builder, Collections.newSetFromMap(new IdentityHashMap<>()));
		return builder.toString();
	}

	private static void toString(Object object, StringBuilder builder, Set<Object> seen) {
		if (object == null) {
			builder.append("null");
			return;
		}
		if (!seen.add(object)) {
			builder.append("...");
			if (object instanceof JSObject) {
				builder.append(" // ").append(((JSObject) object).name);
			}
			return;
		}
		if (!(object instanceof JSObject)) {
			builder.append(object);
			return;
		}
		JSObject jsObject = (JSObject) object;
		builder.append(jsObject.name).append("{ ");
		String separator = "";

		for (Map.Entry<String, Integer> entry : jsObject.hiddenClass.slotMap.entrySet()) {
			builder.append(separator);
			builder.append(entry.getKey()).append(": ");
			toString(jsObject.values[entry.getValue()], builder, seen);
			separator = ", ";
		}
		builder.append(" }");
	}

	private static void toStringPretty(Object object, StringBuilder builder, Set<Object> seen) {
		if (object == null) {
			builder.append("null");
			return;
		}
		if (!seen.add(object)) {
			builder.append("...");
			if (object instanceof JSObject) {
				builder.append(" // ").append(((JSObject) object).name);
			}
			return;
		}
		if (!(object instanceof JSObject)) {
			builder.append(object);
			return;
		}
		JSObject jsObject = (JSObject) object;
		builder.append("{ // ").append(jsObject.name).append('\n');
		jsObject.hiddenClass.slotMap.forEach((key, slot) -> {
			builder.append("  ").append(key).append(": ");
			toString(jsObject.values[slot], builder, seen);
			builder.append("\n");
		});
		builder.append("  proto: ");
		toString(jsObject.proto, builder, seen);
		builder.append("\n");
		builder.append("}");
	}
}