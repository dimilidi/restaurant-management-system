package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

public enum FoodType {
    MEAT("Meat"),
    VEGETABLE("Vegetable");
    private String type;
    FoodType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
