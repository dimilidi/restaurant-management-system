package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid;

import Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products.Product;

import java.util.Collection;

public class Printer {

    private static final String SUM_FORMAT = "Sum: %f";
    private static final String AVERAGE_FORMAT= "Average: %f";

    public static void printSum(double sum) {
        System.out.printf((SUM_FORMAT) + "%n", sum);
    }

    public static void printAverage(double average) {
        System.out.printf((AVERAGE_FORMAT) + "%n", average);
    }

}
