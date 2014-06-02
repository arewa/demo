import java.util.*;
import org.objectweb.asm.*;

public class NabbleDump implements Opcodes {

	public static byte[] dump() throws Exception {

		ClassWriter cw = new ClassWriter(0);
		FieldVisitor fv;
		MethodVisitor mv;
		AnnotationVisitor av0;

		cw.visit(V1_5, ACC_PUBLIC + ACC_FINAL + ACC_SUPER + ACC_ENUM, "Nabble",
				"Ljava/lang/Enum<LNabble;>;", "java/lang/Enum", null);

		cw.visitSource("Nabble.java", null);

		{
			fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM,
					"ASM", "LNabble;", null, null);
			fv.visitEnd();
		}
		{
			fv = cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC + ACC_ENUM,
					"DROOLS", "LNabble;", null, null);
			fv.visitEnd();
		}
		{
			fv = cw.visitField(ACC_PRIVATE + ACC_FINAL + ACC_STATIC
					+ ACC_SYNTHETIC, "ENUM$VALUES", "[LNabble;", null, null);
			fv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(2, l0);
			mv.visitTypeInsn(NEW, "Nabble");
			mv.visitInsn(DUP);
			mv.visitLdcInsn("ASM");
			mv.visitInsn(ICONST_0);
			mv.visitMethodInsn(INVOKESPECIAL, "Nabble", "<init>",
					"(Ljava/lang/String;I)V");
			mv.visitFieldInsn(PUTSTATIC, "Nabble", "ASM", "LNabble;");
			mv.visitTypeInsn(NEW, "Nabble");
			mv.visitInsn(DUP);
			mv.visitLdcInsn("DROOLS");
			mv.visitInsn(ICONST_1);
			mv.visitMethodInsn(INVOKESPECIAL, "Nabble", "<init>",
					"(Ljava/lang/String;I)V");
			mv.visitFieldInsn(PUTSTATIC, "Nabble", "DROOLS", "LNabble;");
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLineNumber(1, l1);
			mv.visitInsn(ICONST_2);
			mv.visitTypeInsn(ANEWARRAY, "Nabble");
			mv.visitInsn(DUP);
			mv.visitInsn(ICONST_0);
			mv.visitFieldInsn(GETSTATIC, "Nabble", "ASM", "LNabble;");
			mv.visitInsn(AASTORE);
			mv.visitInsn(DUP);
			mv.visitInsn(ICONST_1);
			mv.visitFieldInsn(GETSTATIC, "Nabble", "DROOLS", "LNabble;");
			mv.visitInsn(AASTORE);
			mv.visitFieldInsn(PUTSTATIC, "Nabble", "ENUM$VALUES", "[LNabble;");
			mv.visitInsn(RETURN);
			mv.visitMaxs(4, 0);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PRIVATE, "<init>",
					"(Ljava/lang/String;I)V", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(1, l0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitVarInsn(ALOAD, 1);
			mv.visitVarInsn(ILOAD, 2);
			mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Enum", "<init>",
					"(Ljava/lang/String;I)V");
			mv.visitInsn(RETURN);
			Label l1 = new Label();
			mv.visitLabel(l1);
			mv.visitLocalVariable("this", "LNabble;", null, l0, l1, 0);
			mv.visitMaxs(3, 3);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "values",
					"()[LNabble;", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(1, l0);
			mv.visitFieldInsn(GETSTATIC, "Nabble", "ENUM$VALUES", "[LNabble;");
			mv.visitInsn(DUP);
			mv.visitVarInsn(ASTORE, 0);
			mv.visitInsn(ICONST_0);
			mv.visitVarInsn(ALOAD, 0);
			mv.visitInsn(ARRAYLENGTH);
			mv.visitInsn(DUP);
			mv.visitVarInsn(ISTORE, 1);
			mv.visitTypeInsn(ANEWARRAY, "Nabble");
			mv.visitInsn(DUP);
			mv.visitVarInsn(ASTORE, 2);
			mv.visitInsn(ICONST_0);
			mv.visitVarInsn(ILOAD, 1);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "arraycopy",
					"(Ljava/lang/Object;ILjava/lang/Object;II)V");
			mv.visitVarInsn(ALOAD, 2);
			mv.visitInsn(ARETURN);
			mv.visitMaxs(5, 3);
			mv.visitEnd();
		}
		{
			mv = cw.visitMethod(ACC_PUBLIC + ACC_STATIC, "valueOf",
					"(Ljava/lang/String;)LNabble;", null, null);
			mv.visitCode();
			Label l0 = new Label();
			mv.visitLabel(l0);
			mv.visitLineNumber(1, l0);
			mv.visitLdcInsn(Type.getType("LNabble;"));
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/Enum", "valueOf",
					"(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;");
			mv.visitTypeInsn(CHECKCAST, "Nabble");
			mv.visitInsn(ARETURN);
			mv.visitMaxs(2, 1);
			mv.visitEnd();
		}
		cw.visitEnd();

		return cw.toByteArray();
	}
}