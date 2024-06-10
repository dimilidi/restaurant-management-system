package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products;

public abstract class Drink implements HasWeight{
    private double milliliters;

    protected Drink(double milliliters) {

        this.milliliters = milliliters;
    }

    public double getVolumeMilliliters() {

        return milliliters;
    }

    @Override
    public double getWeightGrams() {

        return this.getVolumeMilliliters() * getDensity();
    }

    protected abstract double getDensity();

    public double getVolumeLiters() {
        return getVolumeMilliliters() / 1000;
    }

    @Override
    public double getWeightKg() {
        return getWeightGrams() / 1000;
    }
}
