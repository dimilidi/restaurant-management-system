package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("ROAAR!!!");
    }
    @Override
    public boolean canEatFood(Food food) {
        return food.getClass().getSimpleName().equals(FoodType.MEAT.getType());
    }
}