package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Beverages;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Product;
public class Beverage extends Product {
    private double milliliters;

    public Beverage(String name, BigDecimal price, double milliliters) {
        super(name, price);
        this.milliliters = milliliters;
    }

    public double getMilliliters() {
        return milliliters;
    }
}
