package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products;

public class Chips extends Food implements Product{
    private double weightGrams;

    public Chips(double weightGrams) {
        super(weightGrams);
    }


    @Override
    public double getCaloriesPer100Grams() {
        return 529;
    }
}
