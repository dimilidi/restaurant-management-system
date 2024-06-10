package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.MainDish;

import java.math.BigDecimal;
import Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.Food;
public class MainDish extends Food {
    public MainDish(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
