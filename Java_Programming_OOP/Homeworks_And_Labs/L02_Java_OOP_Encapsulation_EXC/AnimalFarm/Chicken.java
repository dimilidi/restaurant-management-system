package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.AnimalFarm;


import java.util.Locale;

public class Chicken {
    private String name;
    private int age;

    public Chicken(String name, int age) {
        setName(name);
        setAge(age);
    }


    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }
    private void setAge (int age) {
        if (age < 0 || age > 15) {
            throw new IllegalArgumentException("Age should be between 0 and 15.");
        }
        this.age = age;
    }
    public double productPerDay () {
        return this.calculateProductPerDay();
    }
    private double calculateProductPerDay() {
        if (this.age <= 5) {
            return 2;
        } else if (this.age <= 11) {
            return  1;
        } else {
            return  0.75;
        }
    }

    @Override
    public String toString() {
        return String.format(Locale.US,"Chicken %s (age %d) can produce %.2f eggs per day.", name, age, productPerDay());
    }
}
