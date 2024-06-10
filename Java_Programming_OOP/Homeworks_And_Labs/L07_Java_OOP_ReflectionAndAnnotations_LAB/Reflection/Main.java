package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_LAB.Reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class reflection = Reflection.class;
        System.out.printf("%s%n", reflection);
        Class superClass = reflection.getSuperclass();
        System.out.printf("%s%n", superClass);
        Class[] interfaces = reflection.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.printf("%s%n", anInterface);
        }
        Object reflectionObject = reflection.getDeclaredConstructor().newInstance();
        System.out.printf("%s%n",reflectionObject);
    }
}