package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.Animals;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat("Oscar", "Whiskas");
        Animal dog = new Dog("Rocky", "Meat");

        System.out.println(cat.explainSelf());
        System.out.println(dog.explainSelf());
    }
}
