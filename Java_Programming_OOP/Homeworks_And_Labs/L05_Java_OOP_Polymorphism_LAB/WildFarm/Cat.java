package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight, livingRegion);
        this.breed = breed;
    }
    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }
    @Override
    public boolean canEatFood(Food food) {
        return true;
    }
    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %s, %d]",getClass().getSimpleName(), super.getAnimalName(), this.breed, decimalFormat.format(super.getAnimalWeight()), super.getLivingRegion() ,super.getFoodEaten());
    }
}