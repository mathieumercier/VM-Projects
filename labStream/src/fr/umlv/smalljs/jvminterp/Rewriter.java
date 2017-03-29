package fr.umlv.smalljs.jvminterp;

import static java.lang.invoke.MethodType.genericMethodType;
import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ACONST_NULL;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.ARETURN;
import static org.objectweb.asm.Opcodes.ASTORE;
import static org.objectweb.asm.Opcodes.DUP;
import static org.objectweb.asm.Opcodes.GOTO;
import static org.objectweb.asm.Opcodes.H_INVOKESTATIC;
import static org.objectweb.asm.Opcodes.IFEQ;
import static org.objectweb.asm.Opcodes.IFNE;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.POP;
import static org.objectweb.asm.Opcodes.V1_8;

import java.io.PrintWriter;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.util.CheckClassAdapter;

import fr.umlv.smalljs.ast.Block;
import fr.umlv.smalljs.ast.Expr;
import fr.umlv.smalljs.ast.FieldAccess;
import fr.umlv.smalljs.ast.FieldAssignment;
import fr.umlv.smalljs.ast.Fun;
import fr.umlv.smalljs.ast.FunCall;
import fr.umlv.smalljs.ast.If;
import fr.umlv.smalljs.ast.Literal;
import fr.umlv.smalljs.ast.LocalVarAccess;
import fr.umlv.smalljs.ast.LocalVarAssignment;
import fr.umlv.smalljs.ast.MethodCall;
import fr.umlv.smalljs.ast.New;
import fr.umlv.smalljs.ast.Return;
import fr.umlv.smalljs.ast.Visitor;
import fr.umlv.smalljs.ast.VoidVisitor;
import fr.umlv.smalljs.ast.While;
import fr.umlv.smalljs.rt.JSObject;

class Rewriter {
	private final Visitor<JSObject, StackResult> visitor;

	private Rewriter(MethodVisitor mv, Dictionary dictionary) {
		this.visitor = createVisitor(mv, dictionary);
	}

	static JSObject createFunction(Optional<String> name, List<String> parameters, Block body, JSObject global) {
		JSObject env = JSObject.newEnv(null);

		env.register("this", 0);
		for (String parameter : parameters) {
			env.register(parameter, env.length());
		}
		int firstlocal = env.length();
		visitVariable(body, env);
		int lastLocal = env.length();

		ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "script", null, "java/lang/Object", null);
		cv.visitSource("script", null);

		String functionName = name.orElse("lambda");
		MethodType methodType = genericMethodType(1 + parameters.size());
		String desc = methodType.toMethodDescriptorString();
		MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
		mv.visitCode();

		for (int var = firstlocal; var < lastLocal; var++) {
			if (var == firstlocal) {
				mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
			}
			if (var != lastLocal - 1) {
				mv.visitInsn(DUP);
			}
			mv.visitVarInsn(ASTORE, var);
		}

		Dictionary dictionary = new Dictionary();
		Rewriter rewriter = new Rewriter(mv, dictionary);
		rewriter.visitor.visit(body, env).pop(mv);

		mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(0, 0);
		mv.visitEnd();

		byte[] instrs = cv.toByteArray();
		dumpBytecode(instrs);

		FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
		Class<?> type = functionClassLoader.createClass("script", instrs);

		MethodHandle mh;
		try {
			mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			throw new AssertionError(e);
		}

		return JSObject.newFunction(name.orElse("<anonymous>"), mh);
	}

	private static void dumpBytecode(byte[] array) {
		ClassReader reader = new ClassReader(array);
		CheckClassAdapter.verify(reader, true, new PrintWriter(System.out));
	}

	private static void visitVariable(Expr expr, JSObject env) {
		VARIABLE_VISITOR.visit(expr, env);
	}

	private static final VoidVisitor<JSObject> VARIABLE_VISITOR = new VoidVisitor<JSObject>()
			.when(Block.class, (block, env) -> {
				for (Expr instr : block.getInstrs()) {
					visitVariable(instr, env);
				}
			}).when(Literal.class, (literal, env) -> {
				// do nothing
			}).when(FunCall.class, (funCall, env) -> {
				// do nothing
			}).when(LocalVarAssignment.class, (localVarAssignment, env) -> {
				if (localVarAssignment.isDeclaration()) {
					env.register(localVarAssignment.getName(), env.length());
				}
			}).when(LocalVarAccess.class, (localVarAccess, env) -> {
				// do nothing
			}).when(Fun.class, (fun, env) -> {
				// do nothing
			}).when(Return.class, (_return, env) -> {
				// do nothing
			}).when(If.class, (_if, env) -> {
				visitVariable(_if.getTrueBlock(), env);
				visitVariable(_if.getFalseBlock(), env);
			}).when(New.class, (_new, env) -> {
				// do nothing
			}).when(FieldAccess.class, (fieldAccess, env) -> {
				// do nothing
			}).when(FieldAssignment.class, (fieldAssignment, env) -> {
				// do nothing
			}).when(MethodCall.class, (methodCall, env) -> {
				// do nothing
			}).when(While.class, (_while, env) -> {
				// do nothing
			});

	private static Handle bsm(String name, Class<?>... parameterTypes) {
		return new Handle(H_INVOKESTATIC, RT_NAME, name,
				MethodType.methodType(CallSite.class, parameterTypes).toMethodDescriptorString());
	}

	private static final String JSOBJECT = JSObject.class.getName().replace('.', '/');
	private static final String RT_NAME = RT.class.getName().replace('.', '/');
	private static final Handle BSM_UNDEFINED = bsm("bsm_undefined", Lookup.class, String.class, MethodType.class);
	private static final Handle BSM_CONST = bsm("bsm_const", Lookup.class, String.class, MethodType.class, int.class);
	private static final Handle BSM_FUNCALL = bsm("bsm_funcall", Lookup.class, String.class, MethodType.class);
	private static final Handle BSM_CONST_FUNCALL = bsm("bsm_const_funcall", Lookup.class, String.class,
			MethodType.class, String.class);
	private static final Handle BSM_LOOKUP = bsm("bsm_lookup", Lookup.class, String.class, MethodType.class,
			String.class);
	private static final Handle BSM_FUN = bsm("bsm_fun", Lookup.class, String.class, MethodType.class, int.class);
	private static final Handle BSM_REGISTER = bsm("bsm_register", Lookup.class, String.class, MethodType.class,
			String.class);
	private static final Handle BSM_TRUTH = bsm("bsm_truth", Lookup.class, String.class, MethodType.class);
	private static final Handle BSM_GET = bsm("bsm_get", Lookup.class, String.class, MethodType.class, String.class);
	private static final Handle BSM_SET = bsm("bsm_set", Lookup.class, String.class, MethodType.class, String.class);
	private static final Handle BSM_METHODCALL = bsm("bsm_methodcall", Lookup.class, String.class, MethodType.class);

	private enum StackResult {
		EXPR, VOID;

		void pop(MethodVisitor mv) {
			if (this == EXPR) {
				mv.visitInsn(POP);
			}
		}
	}

	private static Visitor<JSObject, StackResult> createVisitor(MethodVisitor mv, Dictionary dictionary) {
		Visitor<JSObject, StackResult> visitor = new Visitor<>();
		visitor.when(Block.class, (block, env) -> {
			for (Expr e : block.getInstrs()) {
				visitor.visit(e, env).pop(mv);
			}
			return StackResult.VOID;
		}).when(Literal.class, (literal, env) -> {
			Object value = literal.getValue();
			if (value instanceof Integer) {
				mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, value);
			} else {
				mv.visitLdcInsn(value);
			}
			return StackResult.EXPR;
		}).when(FunCall.class, (funCall, env) -> {
			visitor.visit(funCall.getQualified(), env);
			List<Expr> args = funCall.getArgs();
			for (Expr expr : args) {
				visitor.visit(expr, env);
			}
			if (funCall.getQualified() instanceof LocalVarAccess) {
				String name = ((LocalVarAccess) funCall.getQualified()).getName();
				if (env.lookup(name) == JSObject.UNDEFINED) {
					mv.visitInvokeDynamicInsn("const_funcall",
							genericMethodType(args.size() + 1).toMethodDescriptorString(), BSM_CONST_FUNCALL, name);
					return StackResult.EXPR;
				}
			}
			mv.visitInvokeDynamicInsn("funcall", genericMethodType(args.size() + 1).toMethodDescriptorString(),
					BSM_FUNCALL);
			return StackResult.EXPR;
		}).when(LocalVarAssignment.class, (localVarAssignment, env) -> {
			visitor.visit(localVarAssignment.getExpr(), env);
			mv.visitVarInsn(ASTORE, (int) env.lookup(localVarAssignment.getName()));
			return StackResult.VOID;
		}).when(LocalVarAccess.class, (localVarAccess, env) -> {
			Object object = env.lookup(localVarAccess.getName());
			if (object == JSObject.UNDEFINED) {
				mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, localVarAccess.getName());
			} else {
				mv.visitVarInsn(ALOAD, (int) object);
			}
			return StackResult.EXPR;
		}).when(Fun.class, (fun, env) -> {
			int id = dictionary.register(fun);
			mv.visitInvokeDynamicInsn("fun", "()Ljava/lang/Object;", BSM_FUN, id);

			fun.getName().ifPresent(name -> {
				mv.visitInsn(DUP);
				mv.visitInvokeDynamicInsn("register", "(Ljava/lang/Object;)V", BSM_REGISTER, name);
			});
			return StackResult.EXPR;
		}).when(Return.class, (_return, env) -> {
			visitor.visit(_return.getExpr(), env);
			mv.visitInsn(ARETURN);
			return StackResult.VOID;
		}).when(If.class, (_if, env) -> {
			Label _else = new Label();
			Label _end = new Label();
			visitor.visit(_if.getCondition(), env);
			mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
			mv.visitJumpInsn(IFEQ, _else);
			visitor.visit(_if.getTrueBlock(), env).pop(mv);
			mv.visitJumpInsn(GOTO, _end);
			mv.visitLabel(_else);
			visitor.visit(_if.getFalseBlock(), env).pop(mv);
			mv.visitLabel(_end);
			return StackResult.VOID;
		}).when(While.class, (_while, env) -> {
			Label test = new Label();
			Label block = new Label();
			mv.visitJumpInsn(GOTO, test);
			mv.visitLabel(block);
			visitor.visit(_while.getBlock(), env);
			mv.visitLabel(test);
			visitor.visit(_while.getCondition(), env);
			mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
			mv.visitJumpInsn(IFNE, block);
			return StackResult.VOID;
		}).when(New.class, (_new, env) -> {
			mv.visitInsn(ACONST_NULL);
			mv.visitMethodInsn(INVOKESTATIC, JSOBJECT, "newObject", "(L" + JSOBJECT + ";)L" + JSOBJECT + ';', false);
			_new.getInitMap().forEach((name, init) -> {
				mv.visitInsn(DUP);
				mv.visitLdcInsn(name);
				visitor.visit(init, env);
				mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
						false);
			});
			return StackResult.EXPR;
		}).when(FieldAccess.class, (fieldAccess, env) -> {
			visitor.visit(fieldAccess.getReceiver(), env);
			mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, fieldAccess.getName());
			return StackResult.EXPR;
		}).when(FieldAssignment.class, (fieldAssignment, env) -> {
			visitor.visit(fieldAssignment.getReceiver(), env);
			visitor.visit(fieldAssignment.getExpr(), env);
			mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET,
					fieldAssignment.getName());
			return StackResult.VOID;
		}).when(MethodCall.class, (methodCall, env) -> {
			visitor.visit(methodCall.getReceiver(), env);
			List<Expr> args = methodCall.getArgs();
			for (Expr expr : args) {
				visitor.visit(expr, env);
			}
			mv.visitInvokeDynamicInsn(methodCall.getName(),
					genericMethodType(1 + args.size()).toMethodDescriptorString(), BSM_METHODCALL);
			return StackResult.EXPR;
		});
		return visitor;
	}
}