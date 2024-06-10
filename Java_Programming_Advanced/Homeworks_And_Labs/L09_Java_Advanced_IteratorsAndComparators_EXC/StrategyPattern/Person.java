package Homeworks_And_Labs.L09_Java_Advanced_IteratorsAndComparators_EXC.StrategyPattern;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("%s %d", name, age);
    }
}
