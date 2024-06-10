package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid;

import Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products.Product;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CalorieCalculator extends Calculator<Product>{
    public CalorieCalculator() {
    }


    private static double calculateCalories(
            double caloriesPer100Grams, double grams) {
        return (caloriesPer100Grams / 100) * grams;
    }


    @Override
    protected double extractValue(Product product) {
        return calculateCalories(product.getCaloriesPer100Grams(), product.getWeightGrams());
    }
}
