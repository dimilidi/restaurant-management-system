package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.CarSalesman;

public class Engine {
    private String model;
    private int power; //мощност
    private int displacement; //работен обем
    private String efficiency; //ефективност

    public Engine(String model, int power, int displacement, String efficiency) {
        //нов празен обект
        this.model = model;
        this.power = power;
        this.displacement = displacement;
        this.efficiency = efficiency;
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getDisplacement() {
        return displacement;
    }

    public String getEfficiency() {
        return efficiency;
    }
}
