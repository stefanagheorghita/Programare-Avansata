package org.example;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM9, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("specialTest")) {
            MethodVisitor newMethodVisitor = new MethodVisitor(Opcodes.ASM9, methodVisitor) {
                @Override
                public void visitCode() {
                    super.visitCode();
                    visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                    visitLdcInsn("\u001B[32mChan\u001B[33mged\u001B[36m byte\u001B[35mcode\u001B[0m");
                    visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                }
            };
            return newMethodVisitor;
        }
        return methodVisitor;
    }


}
