package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

public enum AnimalType {
    CAT("Cat"),
    MOUSE("Mouse"),
    TIGER("Tiger"),
    ZEBRA("Zebra");

    private final String type;

    private AnimalType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }


}
