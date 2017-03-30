package fr.umlv.smalljs.jvminterp;

import fr.umlv.smalljs.rt.JSObject;
import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

import java.io.PrintWriter;
import java.lang.invoke.CallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodType.genericMethodType;
import static org.objectweb.asm.Opcodes.*;

class StreamBuiltins {

    private static Handle bsm(String name, Class<?>... parameterTypes) {
        return new Handle(H_INVOKESTATIC, RT_NAME, name,
                MethodType.methodType(CallSite.class, parameterTypes).toMethodDescriptorString());
    }

    private static final String JSOBJECT = JSObject.class.getName().replace('.', '/');
    private static final String RT_NAME = RT.class.getName().replace('.', '/');
    private static final Handle BSM_UNDEFINED = bsm("bsm_undefined", Lookup.class, String.class, MethodType.class);
    private static final Handle BSM_CONST = bsm("bsm_const", Lookup.class, String.class, MethodType.class, int.class);
    private static final Handle BSM_TRUE = bsm("bsm_true", Lookup.class, String.class, MethodType.class);
    private static final Handle BSM_FALSE = bsm("bsm_false", Lookup.class, String.class, MethodType.class);
    private static final Handle BSM_FUNCALL = bsm("bsm_funcall", Lookup.class, String.class, MethodType.class);
    private static final Handle BSM_CONST_FUNCALL = bsm("bsm_const_funcall", Lookup.class, String.class,
            MethodType.class, String.class);
    private static final Handle BSM_LOOKUP = bsm("bsm_lookup", Lookup.class, String.class, MethodType.class,
            String.class);
    private static final Handle BSM_TRUTH = bsm("bsm_truth", Lookup.class, String.class, MethodType.class);
    private static final Handle BSM_GET = bsm("bsm_get", Lookup.class, String.class, MethodType.class, String.class);
    private static final Handle BSM_SET = bsm("bsm_set", Lookup.class, String.class, MethodType.class, String.class);
    private static final Handle BSM_METHODCALL = bsm("bsm_methodcall", Lookup.class, String.class, MethodType.class);


    private static void dumpBytecode(byte[] array) {
        ClassReader reader = new ClassReader(array);
        CheckClassAdapter.verify(reader, true, new PrintWriter(System.out));
    }

    static JSObject createStreamForEachFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("fun", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_forEach";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        Label test = new Label();
        Label block = new Label();
        mv.visitJumpInsn(GOTO, test);
        mv.visitLabel(block);
        int lookupFun = (int) env.lookup("fun");
        mv.visitVarInsn(ALOAD, lookupFun);
        Object lookupThis = env.lookup("this");
        mv.visitVarInsn(ALOAD, (int) lookupThis);
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInvokeDynamicInsn("funcall", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_FUNCALL);
        mv.visitInsn(POP);
        mv.visitLabel(test);
        mv.visitVarInsn(ALOAD, (int) lookupThis);
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        mv.visitJumpInsn(IFNE, block);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_forEach", mh);
    }

    static JSObject createStreamFilterAdvanceFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("next_tmp", env.length());
        env.register("prev_tmp", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_filter_advance";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(DUP);
        int lookupNextTmp = (int) env.lookup("next_tmp");
        mv.visitVarInsn(ASTORE, lookupNextTmp);
        int lookupPrevTmp = (int) env.lookup("prev_tmp");
        mv.visitVarInsn(ASTORE, lookupPrevTmp);

        Dictionary dictionary = new Dictionary();
        Label l0 = new Label();
        Label l1 = new Label();
        mv.visitJumpInsn(GOTO, l0);
        mv.visitLabel(l1);
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitVarInsn(ASTORE, lookupNextTmp);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitVarInsn(ALOAD, lookupNextTmp);
        mv.visitInvokeDynamicInsn("pred", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitVarInsn(ASTORE, lookupPrevTmp);
        mv.visitVarInsn(ALOAD, lookupPrevTmp);
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        mv.visitJumpInsn(IFEQ, l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("true", "()Ljava/lang/Object;", BSM_TRUE);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "hasnext_tmp");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitVarInsn(ALOAD, lookupNextTmp);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "next_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(ARETURN);
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        mv.visitJumpInsn(IFNE, l1);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "next_tmp");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("false", "()Ljava/lang/Object;", BSM_FALSE);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "hasnext_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_filter_advance", mh);
    }

    static JSObject createStreamFilterHasnextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_filter_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "==");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "hasnext_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "==");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        Label l0 = new Label();
        mv.visitJumpInsn(IFEQ, l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("advance", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "hasnext_tmp");
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_filter_hasnext", mh);
    }

    static JSObject createStreamFilterNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("ret", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_filter_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        int lookupRet = (int) env.lookup("ret");
        mv.visitVarInsn(ASTORE, lookupRet);

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "==");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "hasnext_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "==");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        Label l0 = new Label();
        mv.visitJumpInsn(IFEQ, l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("advance", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "hasnext_tmp");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "next_tmp");
        mv.visitVarInsn(ASTORE, lookupRet);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "next_tmp");
        mv.visitVarInsn(ALOAD, lookupRet);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_filter_next", mh);
    }

    static JSObject createStreamFilterFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("pred", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_filter";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("base");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("pred");
        int lookupPred = (int) env.lookup("pred");
        mv.visitVarInsn(ALOAD, lookupPred);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("hasnext_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("next_tmp");
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("advance");
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "stream_filter_advance");
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("_hasnext");
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "stream_filter_hasnext");
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("_next");
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "stream_filter_next");
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_filter", mh);
    }

    private static void visitStreamMethods(MethodVisitor mv) {
        registerGlobalAsMember(mv, "forEach", "stream_forEach");
        registerGlobalAsMember(mv, "filter", "stream_filter");
        registerGlobalAsMember(mv, "limit", "stream_limit");
        registerGlobalAsMember(mv, "skip", "stream_skip");
        registerGlobalAsMember(mv, "map", "stream_map");
        registerGlobalAsMember(mv, "count", "stream_count");
        registerGlobalAsMember(mv, "findFirst", "stream_find_first");
    }

    private static void registerGlobalAsMember(MethodVisitor mv, String memberName, String globalName) {
        mv.visitInsn(DUP);
        mv.visitLdcInsn(memberName);
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, globalName);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
    }

    static JSObject createStreamLimitHasnextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_limit_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "<");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "k");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "n");
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "<");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        Label l0 = new Label();
        mv.visitJumpInsn(IFEQ, l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitLabel(l0);
        mv.visitInvokeDynamicInsn("false", "()Ljava/lang/Object;", BSM_FALSE);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_limit_hasnext", mh);
    }

    static JSObject createStreamLimitNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_limit_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "<");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "k");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "n");
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "<");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        Label l0 = new Label();
        mv.visitJumpInsn(IFEQ, l0);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "+");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "k");
        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 1);
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "+");
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "k");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitLabel(l0);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_limit_next", mh);
    }

    static JSObject createStreamLimitFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("n", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_limit";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("base");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("n");
        int lookupN = (int) env.lookup("n");
        mv.visitVarInsn(ALOAD, lookupN);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("k");
        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 0);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        registerGlobalAsMember(mv, "_hasnext", "stream_limit_hasnext");
        registerGlobalAsMember(mv, "_next", "stream_limit_next");
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_limit", mh);
    }

    static JSObject createStreamSkipDoSkipFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("k", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_skip_do_skip";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        int lookupK = (int) env.lookup("k");
        mv.visitVarInsn(ASTORE, lookupK);

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "skipped");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        Label l0 = new Label();
        mv.visitJumpInsn(IFEQ, l0);
        Label l1 = new Label();
        mv.visitJumpInsn(GOTO, l1);
        mv.visitLabel(l0);
        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 0);
        mv.visitVarInsn(ASTORE, lookupK);
        Label l2 = new Label();
        mv.visitJumpInsn(GOTO, l2);
        Label l3 = new Label();
        mv.visitLabel(l3);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "+");
        mv.visitVarInsn(ALOAD, lookupK);
        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 1);
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "+");
        mv.visitVarInsn(ASTORE, lookupK);
        mv.visitLabel(l2);
        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "<");
        mv.visitVarInsn(ALOAD, lookupK);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "n");
        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "<");
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        mv.visitJumpInsn(IFNE, l3);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("true", "()Ljava/lang/Object;", BSM_TRUE);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "skipped");
        mv.visitLabel(l1);
        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_skip_do_skip", mh);
    }

    static JSObject createStreamSkipHasNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_skip_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("do_skip", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_skip_hasnext", mh);
    }

    static JSObject createStreamSkipNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_skip_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("do_skip", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_skip_next", mh);
    }

    static JSObject createStreamSkipFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("n", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_skip";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("base");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("n");
        int lookupN = (int) env.lookup("n");
        mv.visitVarInsn(ALOAD, lookupN);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("skipped");
        mv.visitInvokeDynamicInsn("false", "()Ljava/lang/Object;", BSM_FALSE);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        registerGlobalAsMember(mv, "do_skip", "stream_skip_do_skip");
        registerGlobalAsMember(mv, "_hasnext", "stream_skip_hasnext");
        registerGlobalAsMember(mv, "_next", "stream_skip_next");
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_skip", mh);
    }


    static JSObject createGenerateHasnextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "generate_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("true", "()Ljava/lang/Object;", BSM_TRUE);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("generate_hasnext", mh);
    }


    static JSObject createGenerateNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "generate_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("gen", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("generate_next", mh);
    }

    static JSObject createGenerateFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("gen", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "generate";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("gen");
        int lookupGen = (int) env.lookup("gen");
        mv.visitVarInsn(ALOAD, lookupGen);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        registerGlobalAsMember(mv, "_hasnext", "generate_hasnext");
        registerGlobalAsMember(mv, "_next", "generate_next");
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("generate", mh);
    }


    static JSObject createEnumerateHasnextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "enumerate_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitInvokeDynamicInsn("true", "()Ljava/lang/Object;", BSM_TRUE);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("enumerate_hasnext", mh);
    }


    static JSObject createEnumerateNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("next", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "enumerate_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        mv.visitInvokeDynamicInsn("undefined", "()Ljava/lang/Object;", BSM_UNDEFINED);
        int lookupNext = (int) env.lookup("next");
        mv.visitVarInsn(ASTORE, lookupNext);

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "seed");
        mv.visitInvokeDynamicInsn("gen", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitVarInsn(ASTORE, lookupNext);
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitVarInsn(ALOAD, lookupNext);
        mv.visitInvokeDynamicInsn("set", "(Ljava/lang/Object;Ljava/lang/Object;)V", BSM_SET, "seed");
        mv.visitVarInsn(ALOAD, lookupNext);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("enumerate_next", mh);
    }

    static JSObject createEnumerateFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("seed", env.length());
        env.register("gen", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "enumerate";
        MethodType methodType = genericMethodType(1 + 2);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("seed");
        int lookupSeed = (int) env.lookup("seed");
        mv.visitVarInsn(ALOAD, lookupSeed);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("gen");
        int lookupGen = (int) env.lookup("gen");
        mv.visitVarInsn(ALOAD, lookupGen);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        registerGlobalAsMember(mv, "_hasnext", "enumerate_hasnext");
        registerGlobalAsMember(mv, "_next", "enumerate_next");
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("enumerate", mh);
    }


    static JSObject createStreamMapHasnextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_map_hasnext";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        mv.visitVarInsn(ALOAD, (int) env.lookup("this"));
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_map_hasnext", mh);
    }


    static JSObject createStreamMapNextFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_map_next";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitInsn(DUP);
        mv.visitInvokeDynamicInsn("get", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_GET, "base");
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInvokeDynamicInsn("mapper", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_map_next", mh);
    }

    static JSObject createStreamMapFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("mapper", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_map";
        MethodType methodType = genericMethodType(1 + 1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();

        createJSObject(mv);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("base");
        int lookupThis = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, lookupThis);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        mv.visitInsn(DUP);
        mv.visitLdcInsn("mapper");
        int lookupN = (int) env.lookup("mapper");
        mv.visitVarInsn(ALOAD, lookupN);
        mv.visitMethodInsn(INVOKEVIRTUAL, JSOBJECT, "register", "(Ljava/lang/String;Ljava/lang/Object;)V",
                false);
        registerGlobalAsMember(mv, "_hasnext", "stream_map_hasnext");
        registerGlobalAsMember(mv, "_next", "stream_map_next");
        visitStreamMethods(mv);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_map", mh);
    }

    static JSObject createStreamCountFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);
        env.register("counter", env.length());

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_count";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 0);

        int counter_lookup = (int) env.lookup("counter");
        mv.visitVarInsn(ASTORE, counter_lookup);


        Dictionary dictionary = new Dictionary();

        Label l0 = new Label();
        mv.visitJumpInsn(GOTO, l0);

        Label l1 = new Label();
        mv.visitLabel(l1);

        mv.visitInvokeDynamicInsn("lookup", "()Ljava/lang/Object;", BSM_LOOKUP, "+");
        mv.visitVarInsn(ALOAD, counter_lookup);
        mv.visitInvokeDynamicInsn("const", "()Ljava/lang/Object;", BSM_CONST, 1);

        mv.visitInvokeDynamicInsn("const_funcall", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", BSM_CONST_FUNCALL, "+");

        mv.visitVarInsn(ASTORE, counter_lookup);

        int this_lookup = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, this_lookup);

        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(POP);
        mv.visitLabel(l0);
        mv.visitVarInsn(ALOAD, this_lookup);
        mv.visitInvokeDynamicInsn("_hasnext", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInvokeDynamicInsn("truth", "(Ljava/lang/Object;)Z", BSM_TRUTH);
        mv.visitJumpInsn(IFNE, l1);
        mv.visitVarInsn(ALOAD, counter_lookup);
        mv.visitInsn(ARETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_count", mh);
    }

    static JSObject createStreamFindFirstFunction(JSObject global) {
        JSObject env = JSObject.newEnv(null);

        env.register("this", 0);

        ClassWriter cv = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        cv.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "builtin", null, "java/lang/Object", null);
        cv.visitSource("builtin", null);

        String functionName = "stream_find_first";
        MethodType methodType = genericMethodType(1);
        String desc = methodType.toMethodDescriptorString();
        MethodVisitor mv = cv.visitMethod(ACC_PUBLIC | ACC_STATIC, functionName, desc, null, null);
        mv.visitCode();

        Dictionary dictionary = new Dictionary();
        int this_lookup = (int) env.lookup("this");
        mv.visitVarInsn(ALOAD, this_lookup);
        mv.visitInvokeDynamicInsn("_next", "(Ljava/lang/Object;)Ljava/lang/Object;", BSM_METHODCALL);
        mv.visitInsn(ARETURN);

        mv.visitMaxs(0, 0);
        mv.visitEnd();

        byte[] instrs = cv.toByteArray();
        dumpBytecode(instrs);

        FunctionClassLoader functionClassLoader = new FunctionClassLoader(dictionary, global);
        Class<?> type = functionClassLoader.createClass("builtin", instrs);

        MethodHandle mh;
        try {
            mh = MethodHandles.lookup().findStatic(type, functionName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new AssertionError(e);
        }

        return JSObject.newFunction("stream_find_firsto9", mh);
    }

    private static void createJSObject(MethodVisitor mv) {
        mv.visitInsn(ACONST_NULL);
        mv.visitMethodInsn(INVOKESTATIC, JSOBJECT, "newObject", "(L" + JSOBJECT + ";)L" + JSOBJECT + ';', false);
    }

}
