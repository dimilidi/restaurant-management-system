package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Beverages.HotBeverage;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Beverages.Beverage;
public class HotBeverage extends Beverage {
    public HotBeverage(String name, BigDecimal price, double milliliters) {
        super(name, price, milliliters);
    }
}
