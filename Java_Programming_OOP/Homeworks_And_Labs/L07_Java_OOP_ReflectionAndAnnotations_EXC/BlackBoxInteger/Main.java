package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BlackBoxInteger;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Scanner scanner = new Scanner(System.in);

        Class<BlackBoxInt> blackBoxIntClazz = BlackBoxInt.class;
        Constructor<BlackBoxInt> constructor = blackBoxIntClazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        BlackBoxInt blackBoxInt = constructor.newInstance();

        String line = scanner.nextLine();

        while (!"END".equals(line)) {
            String[] tokens = line.split("_");
            String methodName = tokens[0];
            int parameter = Integer.parseInt(tokens[1]);

            Method method = blackBoxIntClazz.getDeclaredMethod(methodName, int.class);
            method.setAccessible(true);
            method.invoke(blackBoxInt, parameter);

            Field innerValue = blackBoxIntClazz.getDeclaredField("innerValue");
            innerValue.setAccessible(true);
            System.out.println(innerValue.get(blackBoxInt));

            line = scanner.nextLine();
        }
    }
}


