package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.VehiclesExtension;

public class Car extends Vehicle {
    public Car(double fuelQuantity, double litersPerKm, double tankCapacity) {
        super(fuelQuantity, litersPerKm, tankCapacity);
    }

    @Override
    protected double getExtraFuelConsumption() {
        return 0.9;
    }
}
