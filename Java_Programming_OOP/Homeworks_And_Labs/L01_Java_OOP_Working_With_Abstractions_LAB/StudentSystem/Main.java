package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.StudentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentSystem studentSystem = new StudentSystem();
        String[] input = scanner.nextLine().split("\\s+");
        while (!input[0].equals("Exit")) {
            studentSystem.executeCommand(input);
            input = scanner.nextLine().split("\\s+");
        }
    }
}
