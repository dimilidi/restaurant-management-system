package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class PasswordValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine();

        System.out.println(validatePassword(password));

    }


    public static String checkValidLength(String password, int minLength, int maxLength) {
        if (password.trim().length() >= minLength && password.trim().length() <= maxLength) {
            return "Valid";
        } else {
            return "Password must be between 6 and 10 characters";
        }
    }

    public static String checkPasswordConsistence(String password) {
        char[] passwordChars = password.toCharArray();

        for (char ch : passwordChars) {
            if (!(Character.isDigit(ch) || Character.isLetter(ch))) {
                return "Password must consist only of letters and digits";
            }
        }
        return "Valid";
    }


    public static String checkMinDigits(String password, int minDigits) {
        char[] passwordChar = password.toCharArray();
        int countDigits = 0;

        for (char ch : passwordChar) {
            if (Character.isDigit(ch)) {
                countDigits++;
            }
        }

        if (countDigits >= minDigits) {
            return "Valid";
        } else {
            return "Password must have at least 2 digits";
        }
    }



    public static String validatePassword(String password) {
        StringBuilder errorMessages = new StringBuilder();

        String validLengthResult = checkValidLength(password, 6, 10);
        String consistenceResult = checkPasswordConsistence(password);
        String minDigitsResult = checkMinDigits(password, 2);

        if (!validLengthResult.equals("Valid")) {
            errorMessages.append(validLengthResult).append("\n");
        }

        if (!consistenceResult.equals("Valid")) {
            errorMessages.append(consistenceResult).append("\n");
        }

        if (!minDigitsResult.equals("Valid")) {
            errorMessages.append(minDigitsResult).append("\n");
        }

        // Check if there are any error messages, and return them
        if (errorMessages.length() > 0) {
            return errorMessages.toString();
        }

        return "Password is valid";
    }


}
