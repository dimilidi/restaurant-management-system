package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class ReplaceRepeatingChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder string = new StringBuilder(scanner.nextLine());
        for (int i = 0; i < string.length() - 1; i++) {
            char letter = string.charAt(i);
            char neighbourChar = string.charAt(i + 1);

            if (letter == neighbourChar) {
                int index = string.indexOf(String.valueOf(neighbourChar));
                string.deleteCharAt(i + 1);
                i = -1;
            }
        }

        System.out.println(string);
    }
}
