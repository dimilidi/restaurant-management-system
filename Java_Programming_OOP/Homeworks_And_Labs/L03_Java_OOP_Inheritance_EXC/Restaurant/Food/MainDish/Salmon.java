package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant.Food.MainDish;

import java.math.BigDecimal;

public class Salmon extends MainDish {
    private static final double SALMON_GRAMS = 22;

    public Salmon(String name, BigDecimal price) {
        super(name, price, SALMON_GRAMS);
    }
}
