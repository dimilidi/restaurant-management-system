package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.ShoppingSpree;

public class Validations {
    public static void validateName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public static void validateMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
    }
}
