package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ValidUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] userNames = scanner.nextLine().split(", ");
        List<String> validStringsList = new ArrayList<>();

        for (String userName : userNames) {
            if (isValidUserName(userName)) {
                validStringsList.add(userName);
            }
        }

        for (String validString : validStringsList) {
            System.out.println(validString);
        }
    }

    public static boolean isValidUserName(String userName) {
        return isInLengthRange(userName) && containsValidCharacters(userName);
    }

    public static boolean isInLengthRange(String string) {
        return string.length() >= 3 && string.length() <= 16;
    }

    public static boolean containsValidCharacters(String string) {
        for (char c : string.toCharArray()) {
            if (!Character.isLetterOrDigit(c) && c != '-' && c != '_') {
                return false;
            }
        }
        return true;
    }
}
