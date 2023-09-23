package L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countNumbers = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= countNumbers ; i++) {
            int sumDigits = addDigits(i);
            boolean isSpecialNumber = checkIfSpecial((sumDigits));
            String booleanAsString = Boolean.toString(isSpecialNumber);
            String booleanAsStringCapitalCase =  booleanAsString.substring(0, 1).toUpperCase() + booleanAsString.substring(1).toLowerCase();
            System.out.printf("%d -> %s%n", i, booleanAsStringCapitalCase);

            // Reset isSpecialNumber for the next number
            isSpecialNumber = false;
        }
    }


    // Function to check if a number is special
    public static boolean checkIfSpecial(int number) {
        return (number == 5 || number == 7 || number == 11);

    }

    // Function to add digits of a number
    public static Integer addDigits(int a) {
        String stringOfInt = Integer.toString(a);
        int sum = 0;
        for (int i = 0; i < stringOfInt.length(); i++) {
            int digit = Character.getNumericValue(stringOfInt.charAt(i));
            sum += digit;
        }
        return sum;
    }
}
