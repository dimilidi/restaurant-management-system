package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_More_Exc;

import java.util.Scanner;

public class Messages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder output = new StringBuilder();
        int textLength = Integer.parseInt(scanner.nextLine());;

        while (textLength > 0) {
            textLength--;

            int digit = Integer.parseInt(scanner.nextLine());
            int digitLength = Integer.toString(digit).length();
            int mainDigit = digit % 10;

            if (digit == 0) {
                output.append(" ");
            } else {
                int offset = (mainDigit - 2) * 3;
                if (mainDigit == 8 || mainDigit == 9) {
                    offset++;
                }

                int letterIndex = offset + digitLength - 1;
                char letter = (char) ('a' + letterIndex); // get ASCII code of the letter ('a'= 97)
                output.append(letter);
            }
        }

        System.out.println(output.toString());



    }
}
