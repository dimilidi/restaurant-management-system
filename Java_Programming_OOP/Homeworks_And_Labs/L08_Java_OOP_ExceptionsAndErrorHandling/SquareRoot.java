package Homeworks_And_Labs.L08_Java_OOP_ExceptionsAndErrorHandling;

import java.util.Scanner;

public class SquareRoot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try {
            int number = Integer.parseInt(input);
            if (number >= 0) {
                Double squareNumber = Math.sqrt(number);
                System.out.printf("%.2f%n", squareNumber);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException exception) {
            System.out.printf("Invalid%n");
        } finally {
            System.out.printf("Goodbye%n");
        }
    }
}
