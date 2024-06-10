package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.Dessert;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.Food;
public class Dessert extends Food {
    private double calories;

    public Dessert(String name, BigDecimal price, double grams, double calories) {
        super(name, price, grams);
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
