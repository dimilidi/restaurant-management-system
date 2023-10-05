package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.VehicleCatalogue;

public class Vehicle {
    private String type;
    private String model;
    private String color;
    private int horsepower;

    public Vehicle (String type, String model, String color, int horsepower) {
        this.type = type;
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
    }

    public String printVehicle (String model) {
        String vehicle = "";
        String capitalizedType = type.substring(0,1).toUpperCase() + type.substring(1);
        if(model.equalsIgnoreCase(this.model)){
            vehicle =  String
                    .format("Type: %s%nModel: %s%nColor: %s%nHorsepower: %s%n",
                    capitalizedType, model, color, horsepower);
        }
        return vehicle;
    }

    public String getType() {
        return type;
    }

    public int getHorsepower () {
        return horsepower;
    }
}
