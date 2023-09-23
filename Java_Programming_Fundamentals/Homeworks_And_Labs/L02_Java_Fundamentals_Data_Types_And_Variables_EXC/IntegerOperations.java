package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class IntegerOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numOne = Integer.parseInt(scanner.nextLine());
        int numTwo = Integer.parseInt(scanner.nextLine());
        int numThree = Integer.parseInt(scanner.nextLine());
        int numFour = Integer.parseInt(scanner.nextLine());
        int sum = numOne + numTwo;
        int division = sum / numThree;
        int multiplication = division * numFour;

        System.out.println(multiplication);

    }
}
