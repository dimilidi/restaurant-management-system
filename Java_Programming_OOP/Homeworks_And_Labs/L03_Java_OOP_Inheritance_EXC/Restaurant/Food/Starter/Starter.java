package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.Starter;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.Food;

public class Starter extends Food {

    public Starter(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
