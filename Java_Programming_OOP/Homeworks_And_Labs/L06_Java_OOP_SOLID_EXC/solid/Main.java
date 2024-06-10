package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid;

import Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
          CalorieCalculator calorieCalculator = new CalorieCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Coke(500));
        products.add(new Lemonade(330));
        products.add(new Chocolate(100));
        products.add(new Chips(200));

        Printer printer = new Printer();

         printer.printSum(calorieCalculator.sum(products));
         printer.printAverage(calorieCalculator.average(products));

         QuantityCalculator quantityCalculator = new QuantityCalculator();

         printer.printSum(quantityCalculator.sum(products));
         printer.printSum(quantityCalculator.average(products));
    }
}
