package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.VehiclesExtension;

public abstract class Vehicle {
    private double fuelQuantity;
    private double fuelConsumption;
    private  double tankCapacity;


    public Vehicle(double fuelQuantity, double litersPerKm, double tankCapacity) {
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = litersPerKm;
        this.tankCapacity = tankCapacity;
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
        if(liters <= 0) {
            throw new IllegalArgumentException("Fuel must be a positive number");
        }

        if(fuelQuantity + liters > tankCapacity ) {
            throw new IllegalArgumentException("Cannot fit fuel in tank");
        }

        this.fuelQuantity += calculateRefuelLitersAfterLoss(liters);
    }

    protected double calculateRefuelLitersAfterLoss(double liters) {
        return liters;
    }
}
