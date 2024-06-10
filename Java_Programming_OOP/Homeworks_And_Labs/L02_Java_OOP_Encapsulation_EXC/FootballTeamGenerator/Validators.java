package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.FootballTeamGenerator;

public class Validators {
    public static void validateName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("A name should not be empty.");
        }
    }
}
