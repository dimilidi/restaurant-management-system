package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.Google;

public class Car {
    private String carModel;
    private int carSpeed;


    public Car(String carModel, int carSpeed) {
        this.carModel = carModel;
        this.carSpeed = carSpeed;
    }

    @Override
    public String toString() {
        return carModel + " " + carSpeed;
    }
}
