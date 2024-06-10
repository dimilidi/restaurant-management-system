package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double animalWeight;
    private Integer foodEaten;

    protected Animal(String animalName, String animalType, Double animalWeight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.animalWeight = animalWeight;
        this.foodEaten = 0;
    }

    protected String getAnimalName() {
        return animalName;
    }

    protected String getAnimalType() {
        return animalType;
    }

    protected Double getAnimalWeight() {
        return animalWeight;
    }

    protected Integer getFoodEaten() {
        return foodEaten;
    }

    public abstract void makeSound();
    public abstract boolean canEatFood(Food food);
    public void eat(Food food) {
        if (canEatFood(food)) {
            foodEaten += food.getQuantity();
        } else {
            System.out.printf("%ss are not eating that type of food!%n",animalType);
        }
    }
}
