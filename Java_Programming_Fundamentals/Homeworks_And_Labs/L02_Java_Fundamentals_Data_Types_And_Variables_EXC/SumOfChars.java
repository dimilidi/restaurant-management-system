package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class SumOfChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberLines = Integer.parseInt(scanner.nextLine());
        int sumAsciiCodes = 0;
        for (int i = 0; i < numberLines; i++) {
            char  letter =  scanner.nextLine().charAt(0);
            int asciiCode = (int) letter;
            sumAsciiCodes += asciiCode;
        }
        System.out.printf("The sum equals: %d", sumAsciiCodes);

    }
}
