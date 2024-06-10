package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.VehiclesExtension;

public class Bus extends  Vehicle{
    boolean nextDriveIsEmpty;
    public Bus(double fuelQuantity, double litersPerKm, double tankCapacity) {
        super(fuelQuantity, litersPerKm, tankCapacity);
    }

    public  void setupEmptyDrive() {
        nextDriveIsEmpty = true;
    }

    @Override
    protected double getExtraFuelConsumption() {
        if(nextDriveIsEmpty) {
            return 0;
        }

        return 1.4;
    }

    @Override
    public Double drive(double distance) {
        Double result =  super.drive(distance);
        nextDriveIsEmpty = false;
        return result;
    }
}
