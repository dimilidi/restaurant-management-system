package FinalExam;

import java.util.Scanner;

public class Problem_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder password = new StringBuilder(scanner.nextLine());

        while (true) {
            String[] command = scanner.nextLine().split("\\s+");

            if (command[0].equals("Complete")) {
                break;
            }

            switch (command[0]) {
                case "Make":
                    makeChange(password, Integer.parseInt(command[2]), command[1]);
                    break;
                case "Insert":
                    insertChar(password, Integer.parseInt(command[1]), command[2]);
                    break;
                case "Replace":
                    replaceChar(password, command[1].charAt(0), Integer.parseInt(command[2]));
                    break;
                case "Validation":
                    isValidPassword(password.toString());
                    break;
            }
        }

        scanner.close();
    }

    private static void makeChange(StringBuilder password, int index, String operation) {
        if (isValidIndex(index, password.length())) {
            switch (operation) {
                case "Upper":
                    password.setCharAt(index, Character.toUpperCase(password.charAt(index)));
                    break;
                case "Lower":
                    password.setCharAt(index, Character.toLowerCase(password.charAt(index)));
                    break;
            }
            System.out.println(password);
        } else {
            System.out.println("Invalid index!");
        }
    }

    private static void insertChar(StringBuilder password, int index, String insertedChar) {
        if (isValidIndex(index, password.length())) {
            password.insert(index, insertedChar);
            System.out.println(password);
        } else {
            System.out.println("Invalid index!");
        }
    }

    private static void replaceChar(StringBuilder password, char targetChar, int value) {
        int index = password.indexOf(String.valueOf(targetChar));
        if (index != -1) {
            char newChar = (char) (targetChar + value);
            password.setCharAt(index, newChar);
            System.out.println(password);
        } else {
            System.out.println("Character not found!");
        }
    }

    private static void isValidPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long!");
            return;
        }

        boolean containsOnlyValidChars = containsOnlyValidChars(password);
        if (!containsOnlyValidChars) {
            System.out.println("Password must consist only of letters, digits and _!");
            return;
        }

        boolean containsUpperCase = containsUpperCase(password);
        if (!containsUpperCase) {
            System.out.println("Password must consist at least one uppercase letter!");
            return;
        }

        boolean containsLowerCase = containsLowerCase(password);
        if (!containsLowerCase) {
            System.out.println("Password must consist at least one lowercase letter!");
            return;
        }

        boolean containsDigit = containsDigit(password);
        if (!containsDigit) {
            System.out.println("Password must consist at least one digit!");
        }
    }

    private static boolean containsOnlyValidChars(String password) {
        return password.matches("[a-zA-Z0-9_]+");
    }

    private static boolean containsUpperCase(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isUpperCase(character)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsLowerCase(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isLowerCase(character)) {
                return true;
            }
        }
        return false;
    }

    private static boolean containsDigit(String password) {
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValidIndex(int index, int length) {
        return index >= 0 && index < length;
    }



}