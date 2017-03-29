package fr.umlv.smalljs.jvminterp;

import static fr.umlv.smalljs.rt.JSObject.UNDEFINED;
import static java.lang.invoke.MethodHandles.constant;
import static java.lang.invoke.MethodHandles.exactInvoker;
import static java.lang.invoke.MethodHandles.foldArguments;
import static java.lang.invoke.MethodHandles.guardWithTest;
import static java.lang.invoke.MethodHandles.insertArguments;
import static java.lang.invoke.MethodType.methodType;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

import fr.umlv.smalljs.ast.Fun;
import fr.umlv.smalljs.ast.Script;
import fr.umlv.smalljs.rt.JSObject;
import fr.umlv.smalljs.rt.JSObject.HiddenClass;

public class RT {
	@SuppressWarnings("unchecked")
	public static void interpret(Script script) {
		JSObject global = JSObject.newEnv(null);
		global.register("global", global);
		global.register("print", JSObject.newFunction("print", args -> {
			System.out.println(Arrays.stream(args).skip(1).map(Object::toString).collect(Collectors.joining(" ")));
			return UNDEFINED;
		}));
		global.register("+", JSObject.newFunction("+", (__, arg1, arg2) -> (Integer) arg1 + (Integer) arg2));
		global.register("-", JSObject.newFunction("-", (__, arg1, arg2) -> (Integer) arg1 - (Integer) arg2));
		global.register("/", JSObject.newFunction("/", (__, arg1, arg2) -> (Integer) arg1 / (Integer) arg2));
		global.register("*", JSObject.newFunction("*", (__, arg1, arg2) -> (Integer) arg1 * (Integer) arg2));
		global.register("%", JSObject.newFunction("%", (__, arg1, arg2) -> (Integer) arg1 % (Integer) arg2));

		global.register("==", JSObject.newFunction("==", (__, arg1, arg2) -> arg1.equals(arg2)));
		global.register("!=", JSObject.newFunction("!=", (__, arg1, arg2) -> !arg1.equals(arg2)));
		global.register("<",
				JSObject.newFunction("<", (__, arg1, arg2) -> (((Comparable<Object>) arg1).compareTo(arg2) < 0)));
		global.register("<=",
				JSObject.newFunction("<=", (__, arg1, arg2) -> (((Comparable<Object>) arg1).compareTo(arg2) <= 0)));
		global.register(">",
				JSObject.newFunction(">", (__, arg1, arg2) -> (((Comparable<Object>) arg1).compareTo(arg2) > 0)));
		global.register(">=",
				JSObject.newFunction(">=", (__, arg1, arg2) -> (((Comparable<Object>) arg1).compareTo(arg2) >= 0)));
		
		global.register("stream_forEach", StreamBuiltins.createStreamForEachFunction(global));
		global.register("stream_filter_advance", StreamBuiltins.createStreamFilterAdvanceFunction(global));
		global.register("stream_filter_hasnext", StreamBuiltins.createStreamFilterHasnextFunction(global));
		global.register("stream_filter_next", StreamBuiltins.createStreamFilterNextFunction(global));
		global.register("stream_filter", StreamBuiltins.createStreamFilterFunction(global));
		global.register("stream_limit_hasnext", StreamBuiltins.createStreamLimitHasnextFunction(global));
		global.register("stream_limit_next", StreamBuiltins.createStreamLimitNextFunction(global));
		global.register("stream_limit", StreamBuiltins.createStreamLimitFunction(global));
		global.register("stream_skip_do_skip", StreamBuiltins.createStreamSkipDoSkipFunction(global));
		global.register("stream_skip_hasnext", StreamBuiltins.createStreamSkipHasNextFunction(global));
		global.register("stream_skip_next", StreamBuiltins.createStreamSkipNextFunction(global));
		global.register("stream_skip", StreamBuiltins.createStreamSkipFunction(global));
		global.register("generate_hasnext", StreamBuiltins.createGenerateHasnextFunction(global));
		global.register("generate_next", StreamBuiltins.createGenerateNextFunction(global));
		global.register("generate", StreamBuiltins.createGenerateFunction(global));
		global.register("enumerate_hasnext", StreamBuiltins.createEnumerateHasnextFunction(global));
		global.register("enumerate_next", StreamBuiltins.createEnumerateNextFunction(global));
		global.register("enumerate", StreamBuiltins.createEnumerateFunction(global));
		global.register("stream_map_hasnext", StreamBuiltins.createStreamMapHasnextFunction(global));
		global.register("stream_map_next", StreamBuiltins.createStreamMapNextFunction(global));
		global.register("stream_map", StreamBuiltins.createStreamMapFunction(global));

		JSObject main = Rewriter.createFunction(Optional.of("main"), Collections.emptyList(), script.getBody(), global);
		main.invoke(new Object[] { UNDEFINED });
	}

	public static CallSite bsm_undefined(Lookup lookup, String name, MethodType type) {
		return new ConstantCallSite(constant(Object.class, UNDEFINED));
	}

	public static CallSite bsm_const(Lookup lookup, String name, MethodType type, int constant) {
		return new ConstantCallSite(constant(Object.class, constant));
	}

	public static CallSite bsm_true(Lookup lookup, String name, MethodType type) {
		return new ConstantCallSite(constant(Object.class, true));
	}

	public static CallSite bsm_false(Lookup lookup, String name, MethodType type) {
		return new ConstantCallSite(constant(Object.class, false));
	}

	static class FunCallOptimizerCS extends MutableCallSite {
		private static final MethodHandle IDENTITY_CHECK, FIRST_CALL;
		static {
			Lookup lookup = MethodHandles.lookup();
			try {
				MethodType mt_check = methodType(boolean.class, Object.class, Object.class);
				MethodType mt_call = methodType(Object.class, JSObject.class, Object[].class);
				IDENTITY_CHECK = lookup.findStatic(FunCallOptimizerCS.class, "identityCheck", mt_check);
				FIRST_CALL = lookup.findVirtual(FunCallOptimizerCS.class, "firstCall", mt_call);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}

		FunCallOptimizerCS(MethodType type) {
			super(type);
			/*
			 * firstCall(JSObject, Object[]) -> this.firstCall(JSObject,
			 * Object[]) -> this.firstCall (JSObject, Object, Object, etc) ->
			 * this.firstCall(JSObject, UNDEFINED, Object, etc) -> make it look
			 * like MethodType
			 */
			setTarget(insertArguments(FIRST_CALL.bindTo(this).asCollector(Object[].class, type().parameterCount()), 1,
					UNDEFINED).asType(type));
		}

		Object firstCall(JSObject function, Object[] arguments) throws Throwable {
			MethodHandle _test = insertArguments(IDENTITY_CHECK, 1, function);
			MethodHandle _true = function.getMethodHandle().asType(type());
			setTarget(guardWithTest(_test, _true, getTarget()));

			return _true.invokeWithArguments(arguments);
		}

		static boolean identityCheck(Object o, Object constant) {
			return o == constant;
		}
	}

	public static CallSite bsm_funcall(Lookup lookup, String name, MethodType type)
			throws NoSuchMethodException, IllegalAccessException {
		// MethodType mt = methodType(Object.class, Object[].class);
		// MethodHandle mh = lookup.findVirtual(JSObject.class, "invoke", mt);
		// mh = mh.asCollector(Object[].class, type.parameterCount());
		// mh = insertArguments(mh, 1, UNDEFINED);
		// return new ConstantCallSite(mh.asType(type));
		return new FunCallOptimizerCS(type);
	}

	static class ConstFunCallOptimizerCS extends MutableCallSite {
		private static final MethodHandle FIRST_CALL;
		static {
			Lookup lookup = MethodHandles.lookup();
			try {
				MethodType mt_call = methodType(Object.class, JSObject.class, Object[].class);
				FIRST_CALL = lookup.findVirtual(ConstFunCallOptimizerCS.class, "firstCall", mt_call);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}

		ConstFunCallOptimizerCS(MethodType type, String funName) {
			super(type);
			/*
			 * firstCall(JSObject, Object[]) -> this.firstCall(JSObject,
			 * Object[]) -> this.firstCall (JSObject, Object, Object, etc) ->
			 * this.firstCall(JSObject, UNDEFINED, Object, etc) -> make it look
			 * like MethodType
			 */
			setTarget(insertArguments(FIRST_CALL.bindTo(this).asCollector(Object[].class, type().parameterCount()), 1,
					UNDEFINED).asType(type));
		}

		Object firstCall(JSObject function, Object[] arguments) throws Throwable {
			MethodHandle _true = function.getMethodHandle().asType(type());
			setTarget(_true);

			return _true.invokeWithArguments(arguments);
		}
	}

	public static CallSite bsm_const_funcall(Lookup lookup, String name, MethodType type, String funName)
			throws NoSuchMethodException, IllegalAccessException {
		// MethodType mt = methodType(Object.class, Object[].class);
		// MethodHandle mh = lookup.findVirtual(JSObject.class, "invoke", mt);
		// mh = mh.asCollector(Object[].class, type.parameterCount());
		// mh = insertArguments(mh, 1, UNDEFINED);
		// return new ConstantCallSite(mh.asType(type));
		return new ConstFunCallOptimizerCS(type, funName);
	}

	public static CallSite bsm_lookup(Lookup lookup, String name, MethodType type, String functionName) {
		FunctionClassLoader cl = ((FunctionClassLoader) lookup.lookupClass().getClassLoader());
		JSObject global = cl.getGlobal();
		JSObject function = (JSObject) global.lookup(functionName);
		return new ConstantCallSite(constant(Object.class, function));
	}

	public static CallSite bsm_fun(Lookup lookup, String name, MethodType type, int functionId) {
		FunctionClassLoader cl = ((FunctionClassLoader) lookup.lookupClass().getClassLoader());
		Dictionary dictionary = cl.getDictionary();
		Fun fun = (Fun) dictionary.lookup(functionId);
		JSObject function = Rewriter.createFunction(fun.getName(), fun.getParameters(), fun.getBody(), cl.getGlobal());
		return new ConstantCallSite(constant(Object.class, function));
	}

	public static CallSite bsm_register(Lookup lookup, String name, MethodType type, String functionName)
			throws NoSuchMethodException, IllegalAccessException {
		FunctionClassLoader cl = ((FunctionClassLoader) lookup.lookupClass().getClassLoader());
		JSObject global = cl.getGlobal();
		MethodType mt = methodType(void.class, String.class, Object.class);
		MethodHandle mh = lookup.findVirtual(JSObject.class, "register", mt);
		mh = insertArguments(mh, 0, global, functionName);
		return new ConstantCallSite(mh);
	}

	public static CallSite bsm_truth(Lookup lookup, String name, MethodType type)
			throws NoSuchMethodException, IllegalAccessException {
		MethodType mt = methodType(boolean.class);
		MethodHandle mh = lookup.findVirtual(Boolean.class, "booleanValue", mt);
		return new ConstantCallSite(mh.asType(type));
	}

	static class FieldGetterCS extends MutableCallSite {
		private static final MethodHandle SLOWGET, HIDDEN_CLASS_CHECK, ARRAY_ACCESS;
		static {
			Lookup lookup = MethodHandles.lookup();
			try {
				MethodType mt_sloget = methodType(Object.class, JSObject.class);
				MethodType mt_check = methodType(boolean.class, JSObject.class, HiddenClass.class);
				MethodType mt_access = methodType(Object.class, JSObject.class, int.class);
				SLOWGET = lookup.findVirtual(FieldGetterCS.class, "slowget", mt_sloget);
				HIDDEN_CLASS_CHECK = lookup.findStatic(FieldGetterCS.class, "hiddenClassCheck", mt_check);
				ARRAY_ACCESS = lookup.findStatic(FieldGetterCS.class, "arrayAccess", mt_access);
			} catch (NoSuchMethodException | IllegalAccessException e) {
				throw new AssertionError(e);
			}
		}

		private final String fieldName;

		FieldGetterCS(MethodType type, String fieldName) {
			super(type);
			this.fieldName = fieldName;
			setTarget(SLOWGET.bindTo(this).asType(type));
		}

		Object slowget(JSObject object) {
			// return object.lookup(fieldName);
			HiddenClass hiddenClass = object.getHiddenClass();
			int slot = hiddenClass.slot(fieldName);
			MethodType type = type();
			setTarget(guardWithTest(
					insertArguments(HIDDEN_CLASS_CHECK, 1, hiddenClass).asType(type.changeReturnType(boolean.class)),
					insertArguments(ARRAY_ACCESS, 1, slot).asType(type), getTarget()));
			return arrayAccess(object, slot);
		}

		static boolean hiddenClassCheck(JSObject jsObject, HiddenClass hiddenClass) {
			return jsObject.getHiddenClass() == hiddenClass;
		}

		static Object arrayAccess(JSObject jsObject, int slot) {
			return jsObject.getValueArray()[slot];
		}
	}

	public static CallSite bsm_get(Lookup lookup, String name, MethodType type, String fieldName)
			throws NoSuchMethodException, IllegalAccessException {
		// MethodType mt = methodType(Object.class, String.class);
		// MethodHandle mh = lookup.findVirtual(JSObject.class, "lookup", mt);
		// mh = insertArguments(mh, 1, fieldName);
		// return new ConstantCallSite(mh.asType(type));
		return new FieldGetterCS(type, fieldName);
	}

	public static CallSite bsm_set(Lookup lookup, String name, MethodType type, String fieldName)
			throws NoSuchMethodException, IllegalAccessException {
		MethodType mt = methodType(void.class, String.class, Object.class);
		MethodHandle mh = lookup.findVirtual(JSObject.class, "register", mt);
		mh = insertArguments(mh, 1, fieldName);
		return new ConstantCallSite(mh.asType(type));
	}

	private static MethodHandle getImplementation(Object receiver, String name) {
		JSObject function = (JSObject) ((JSObject) receiver).lookup(name);
		return function.getMethodHandle();
	}

	public static CallSite bsm_methodcall(Lookup lookup, String name, MethodType type)
			throws NoSuchMethodException, IllegalAccessException {
		MethodType mt = methodType(MethodHandle.class, Object.class, String.class);
		MethodHandle impl = MethodHandles.lookup().findStatic(RT.class, "getImplementation", mt);
		impl = insertArguments(impl, 1, name);
		MethodHandle mh = foldArguments(exactInvoker(type), impl);
		MethodHandle asType = mh.asType(type);
		return new ConstantCallSite(asType);
	}
}
