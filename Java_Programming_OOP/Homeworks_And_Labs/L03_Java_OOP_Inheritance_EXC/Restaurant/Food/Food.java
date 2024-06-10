package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food;

import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Product;

import java.math.BigDecimal;

public class Food extends Product {

    private double grams;

    public Food(String name, BigDecimal price, double grams) {
        super(name, price);
        this.grams = grams;
    }

    public double getGrams() {
        return grams;
    }
}
