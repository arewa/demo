import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.NotFoundException;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlEnumValue;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import sun.reflect.ConstructorAccessor;
import sun.reflect.FieldAccessor;
import sun.reflect.ReflectionFactory;

public class DynamicEnumTest {

	private static ReflectionFactory reflectionFactory = ReflectionFactory
			.getReflectionFactory();

	private static void setFailsafeFieldValue(Field field, Object target,
			Object value) throws NoSuchFieldException, IllegalAccessException {

		// let's make the field accessible
		field.setAccessible(true);

		// next we change the modifier in the Field instance to
		// not be final anymore, thus tricking reflection into
		// letting us modify the static final field
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		int modifiers = modifiersField.getInt(field);

		// blank out the final bit in the modifiers int
		modifiers &= ~Modifier.FINAL;
		// modifiers &= ~Modifier.STATIC;
		modifiersField.setInt(field, modifiers);

		FieldAccessor fa = reflectionFactory.newFieldAccessor(field, false);
		fa.set(target, value);
	}

	private static void blankField(Class<?> enumClass, String fieldName)
			throws NoSuchFieldException, IllegalAccessException {
		for (Field field : Class.class.getDeclaredFields()) {
			if (field.getName().contains(fieldName)) {
				AccessibleObject.setAccessible(new Field[] { field }, true);
				setFailsafeFieldValue(field, enumClass, null);
				break;
			}
		}
	}

	private static void cleanEnumCache(Class<?> enumClass)
			throws NoSuchFieldException, IllegalAccessException {
		blankField(enumClass, "enumConstantDirectory"); // Sun (Oracle?!?) JDK
														// 1.5/6
		blankField(enumClass, "enumConstants"); // IBM JDK
	}

	private static ConstructorAccessor getConstructorAccessor(
			Class<?> enumClass, Class<?>[] additionalParameterTypes)
			throws NoSuchMethodException {
		Class<?>[] parameterTypes = new Class[additionalParameterTypes.length + 3];
		parameterTypes[0] = String.class;
		parameterTypes[1] = int.class;
		parameterTypes[2] = String.class;
		System.arraycopy(additionalParameterTypes, 0, parameterTypes, 3,
				additionalParameterTypes.length);
		return reflectionFactory.newConstructorAccessor(enumClass
				.getDeclaredConstructor(parameterTypes));
	}

	private static Object makeEnum(Class<?> enumClass, String key,
			String value, int ordinal, Class<?>[] additionalTypes,
			Object[] additionalValues) throws Exception {
		Object[] parms = new Object[additionalValues.length + 3];
		parms[0] = key;
		parms[1] = Integer.valueOf(ordinal);
		parms[2] = value;
		System.arraycopy(additionalValues, 0, parms, 3, additionalValues.length);
		return enumClass
				.cast(getConstructorAccessor(enumClass, additionalTypes)
						.newInstance(parms));
	}

	/**
	 * Add an enum instance to the enum class given as argument
	 * 
	 * @param <T>
	 *            the type of the enum (implicit)
	 * @param enumType
	 *            the class of the enum to be modified
	 * @param enumKey
	 *            the name of the new enum instance to be added to the class.
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?>> void replaceEnum(Class<T> enumType,
			String enumKey, String enumValue) {

		// 0. Sanity checks
		if (!Enum.class.isAssignableFrom(enumType)) {
			throw new RuntimeException("class " + enumType
					+ " is not an instance of Enum");
		}

		// 1. Lookup "$VALUES" holder in enum class and get previous enum
		// instances
		Field valuesField = null;
		Field[] fields = TenderClassType.class.getDeclaredFields();
		for (Field field : fields) {
			if (field.getName().contains("$VALUES")) {
				valuesField = field;
				break;
			}
		}
		AccessibleObject.setAccessible(new Field[] { valuesField }, true);

		try {

			// 2. Copy it
			T[] previousValues = (T[]) valuesField.get(enumType);
			List<T> values = new ArrayList<T>(Arrays.asList(previousValues));

			// 3. build new enum
			T newValue = (T) makeEnum(enumType, // The target enum class
					enumKey, // THE NEW ENUM INSTANCE TO BE DYNAMICALLY ADDED
					enumValue, values.size(), new Class<?>[] {}, // could be
																	// used to
																	// pass
																	// values to
																	// the enum
																	// constuctor
																	// if needed
					new Object[] {}); // could be used to pass values to the
										// enum constuctor if needed

			// 4. add new value
			values.add(newValue);

			// 5. Set new values field
			setFailsafeFieldValue(valuesField, null,
					values.toArray((T[]) Array.newInstance(enumType, 0)));

			// 6. Clean enum cache
			cleanEnumCache(enumType);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	// private static enum TestEnum {
	// a,
	// b,
	// c;
	// };

	public static void main(String[] args) throws Exception {

		// for (Constructor con :
		// TenderClassType.class.getDeclaredConstructors()) {
		// System.out.println(con.toString());
		// }

		// Dynamically add 3 new enum instances d, e, f to TestEnum
		// replaceEnum(TenderClassType.class, "PRIVATE_LABEL_CREDIT_CARD",
		// "PrivateLabelCreditCard");
		// setFailsafeFieldValue()
		// replaceEnum(TenderClassType.class, "PRIVATE_LABEL_CREDIT_CARD1",
		// "PrivateLabelCreditCard1");

		// CtClass t = ClassPool.getDefault().get("TenderClassType");
		// t.removeField(t.getField("CREDIT_CARD"));

		// CtField f =
		// CtField.make("PRIVATE_LABEL_CREDIT_CARD(\"PrivateLabelCreditCard\")",
		// t);
		// t.addField(f);

		// Run a few tests just to show it works OK.
		// System.out.println(Arrays.deepToString(TenderClassType.values()));
		// Shows : [a, b, c, d, e, f]

		// CtClass t = ClassPool.getDefault().get("TenderClassType");
		// System.out.println(t.toBytecode().toString());
		
//		System.out.println(Arrays.deepToString(TenderClassType.values()));
//		
//		//loadClass(TenderClassTypeDump.dump());
//		
//		//ClassDefinition definition = new ClassDefinition(TenderClassType.class, TenderClassTypeDump.dump());
//		
//
////		InputStream fin = new ByteArrayInputStream(TenderClassTypeDump.dump());
////		ClassFile cf = new ClassFile(new DataInputStream(fin));
//		
//		File classFile = new File(TenderClassType.class.getResource("TenderClassType.class").getPath());
//        
//        //Write the output to a class file
//        DataOutputStream dout = new DataOutputStream(new FileOutputStream(classFile));
//        dout.write(TenderClassTypeDump.dump());
//		
//		System.out.println(Arrays.deepToString(TenderClassType.values()));
		
		
		
		System.out.println(new Double("4.00").doubleValue());
		
	}
}