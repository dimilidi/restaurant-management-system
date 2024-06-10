package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products;

public class Coke extends Drink implements Product{

    public static final double CALORIES_PER_100_GRAMS = 44.0;
    public static final double DENSITY = 0.6;

    public Coke(double milliliters) {
        super(milliliters);
    }

    @Override
    protected double getDensity() {
        return DENSITY;
    }


    @Override
    public double getCaloriesPer100Grams() {
        return CALORIES_PER_100_GRAMS;
    }
}
