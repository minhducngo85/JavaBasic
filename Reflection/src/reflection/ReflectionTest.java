package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class ReflectionTest {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException,
            InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        // object fields
        System.out.println("--- Field of Class---");
        Class<?> class_ = Class.forName("reflection.MyOuterClass");
        Object obj = class_.newInstance();
        String name = "id";
        try {
            Field field = obj.getClass().getDeclaredField(name);
            Object type = field.getType();
            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println("Object member, name: " + name + ", type: " + type + ", value: " + value);
        } catch (NoSuchFieldException e) {
            System.out.println("Field name: " + name + " not found");
        }

        System.out.println("---Static Fields of Inner Static Class---");
        Class<?> staticInnerClass = Class.forName("reflection.MyOuterClass$StaticInnerClass");
        Object object = staticInnerClass.newInstance();
        // all static fields
        Field[] fields = object.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            Field staticfield = fields[i];
            String fName = staticfield.getName();
            Object fType = staticfield.getType();
            Object fValue = staticfield.get(object);
            System.out.println("Class member, name: " + fName + ", type: " + fType + ", value: " + fValue);
        }

        // one static field: e.g static field name HONK
        Field fieldHonk = object.getClass().getField("STATIC_FIELD_1");
        String fName = fieldHonk.getName();
        Object fType = fieldHonk.getType();
        Object fValue = fieldHonk.get(object);
        System.out.println("Class member, name: " + fName + ", type: " + fType + ", value: " + fValue);

        // inner class is not static
        System.out.println("---Static Fields of Inner Class (Not Static)---");
        Class<?> outerClass = Class.forName("reflection.MyOuterClass");
        Object outerInstance = outerClass.newInstance();

        Class<?> innerClass = Class.forName("reflection.MyOuterClass$InnerClass");
        Constructor<?> ctor = innerClass.getDeclaredConstructor(outerClass);
        Object innerInstance = ctor.newInstance(outerInstance);

        // one static field: e.g static field name HONK
        Field fieldHonk1 = innerInstance.getClass().getField("STATIC_FIELD_4");
        String fName1 = fieldHonk1.getName();
        Object fType1 = fieldHonk1.getType();
        Object fValue1 = fieldHonk1.get(outerInstance);
        System.out.println("Class member, name: " + fName1 + ", type: " + fType1 + ", value: " + fValue1);
    }
}
