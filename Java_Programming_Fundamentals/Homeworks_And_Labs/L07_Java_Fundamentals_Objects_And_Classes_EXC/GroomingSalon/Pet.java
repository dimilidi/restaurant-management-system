package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.GroomingSalon;

public class Pet {
    String name;
    int age;
    String owner;

    public Pet(String name, int age, String owner) {
        this.name = name;
        this.age = age;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return String.format("%s %d - (%s)", name, age, owner);
    }

}
