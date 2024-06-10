package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.Animals;

public abstract class Animal {
    private String name;
    private String favouriteFood;

    protected Animal(String name, String favouriteFood) {
        this.name = name;
        this.favouriteFood = favouriteFood;
    }

    public String getName() {
        return name;
    }

    public String getFavouriteFood() {
        return favouriteFood;
    }

    public String explainSelf() {
        return String.format("I am %s and my favourite food is %s", getName(), getFavouriteFood());
    }
}