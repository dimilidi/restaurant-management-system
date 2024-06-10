package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_LAB;

/*
Reflection in Java is a powerful feature that allows a program to examine or "reflect" upon itself at runtime.
With reflection, you can inspect classes, interfaces, fields, and methods,
invoke methods dynamically, and even create new instances of classes.
It provides a way to obtain information about classes, interfaces, fields, and methods at runtime.
 */


import java.lang.reflect.Method;

public class ReflectionExample {
    public static void main(String[] args) throws Exception {
        // Get the class object for String
        Class<?> stringClass = String.class;

        // Get the methods of String class
        Method[] methods = stringClass.getMethods();

        // Print the names of methods
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        // Create an instance of String class dynamically
        String str = (String) stringClass.getDeclaredConstructor(String.class).newInstance("Hello");
        System.out.println(str);  // Output: Hello

        // Invoke a method dynamically
        Method lengthMethod = stringClass.getMethod("length");
        int length = (int) lengthMethod.invoke(str);
        System.out.println("Length of the string: " + length);  // Output: Length of the string: 5
    }
}
