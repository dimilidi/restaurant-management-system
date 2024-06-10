package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

public class Mouse extends Mammal {
    public Mouse(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    public void eat(Food food) {
        if (!canEatFood(food)) {
            System.out.printf("Mice are not eating that type of food!%n");
        } else {
            super.eat(food);
        }
    }
}
