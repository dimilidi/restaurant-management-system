package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products;

public abstract class Food implements HasWeight{
   private double weightGrams;

    protected Food(double weightGrams) {
        this.weightGrams = weightGrams;
    }

    public double getWeightGrams() {
        return weightGrams;
    }
    @Override
    public double getWeightKg() {
        return getWeightGrams() / 1000;
    }
}
