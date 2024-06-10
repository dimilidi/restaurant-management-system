package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Animal> animals = new ArrayList<>();
        String command = scanner.nextLine();

        while (!"End".equals(command)) {
            Animal animal = createAnimal(command);

            command = scanner.nextLine();
            Food food = createFood(command);

            animal.makeSound();
            animal.eat(food);
            animals.add(animal);

            command = scanner.nextLine();
        }

        for (Animal animal : animals) {
            System.out.printf("%s%n",animal);
        }
    }

    private static Animal createAnimal(String command) {
        String[] commandParts = command.split("\\s+");
        String type = commandParts[0];
        Animal animal = null;

        if (type.equals(AnimalType.CAT.getType())) {
            animal =  new Cat(commandParts[1], commandParts[0], Double.parseDouble(commandParts[2]), commandParts[3], commandParts[4]);
        } else if (type.equals(AnimalType.MOUSE.getType())) {
            animal =  new Mouse(commandParts[1], commandParts[0], Double.parseDouble(commandParts[2]), commandParts[3]);
        } else if (type.equals(AnimalType.TIGER.getType())) {
            animal = new Tiger(commandParts[1], commandParts[0], Double.parseDouble(commandParts[2]), commandParts[3]);
        } else if (type.equals(AnimalType.ZEBRA.getType())) {
            animal = new Zebra(commandParts[1], commandParts[0], Double.parseDouble(commandParts[2]), commandParts[3]);
        }
        return animal;

    }

    private static Food createFood(String command) {
        String[] commandParts = command.split("\\s+");
        String foodType = commandParts[0];
        Food food = null;
        if (foodType.equals(FoodType.MEAT.getType())) {
            food = new Meat(Integer.parseInt(commandParts[1]));
        } else if (foodType.equals(FoodType.VEGETABLE.getType())) {
            food = new Vegetable(Integer.parseInt(commandParts[1]));
        }
        return food;
    }
}

