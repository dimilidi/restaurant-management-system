package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.VehiclesExtension;

public class Truck extends Vehicle {
    public Truck(double fuelQuantity, double litersPerKm, double tankCapacity) {
        super(fuelQuantity, litersPerKm, tankCapacity);
    }

    @Override
    protected double getExtraFuelConsumption() {
        return 1.6;
    }

    @Override
    protected double calculateRefuelLitersAfterLoss(double liters) {
        return liters * 0.95;
    }
}
