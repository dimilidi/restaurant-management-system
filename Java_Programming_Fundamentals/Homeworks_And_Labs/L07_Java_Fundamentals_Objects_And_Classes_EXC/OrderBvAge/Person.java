package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.OrderBvAge;

public class Person {
    String name;
    String id;
    int age;

    public Person (String name, String id, int age){
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String toString() {
        return String.format("%s with ID: %s is %d years old.", name, id, age);
    }
}
