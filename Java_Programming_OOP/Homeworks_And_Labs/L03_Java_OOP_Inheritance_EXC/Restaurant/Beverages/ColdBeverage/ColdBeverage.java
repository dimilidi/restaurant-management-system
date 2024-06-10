package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Beverages.ColdBeverage;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Beverages.Beverage;

public class ColdBeverage extends Beverage {
    public ColdBeverage(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }
}
