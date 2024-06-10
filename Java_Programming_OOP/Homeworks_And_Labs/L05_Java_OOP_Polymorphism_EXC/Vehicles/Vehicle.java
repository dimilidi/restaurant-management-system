package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Vehicles;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;


    public Vehicle(double fuelQuantity, double litersPerKm) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = litersPerKm;
    }

    public double getFuelQuantity() {
        return fuelQuantity;
    }


    public  Double drive(double distance){
        double fuelRequired =  distance * (fuelConsumption + getExtraFuelConsumption());
        if(fuelRequired > fuelQuantity) {
            return null;
        }
         fuelQuantity -= fuelRequired;
        return distance;
    }

    protected abstract double getExtraFuelConsumption();

    public void refuel(double liters) {
        this.fuelQuantity += calculateRefuelLitersAfterLoss(liters);
    }

    protected double calculateRefuelLitersAfterLoss(double liters) {
        return liters;
    }
}
