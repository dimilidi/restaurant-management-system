package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_LAB;

import java.util.Scanner;

public class ReverseStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("end")) {
            String reversed = "";
            for (int i = command.length() - 1; i >= 0; i--) {
                reversed += command.charAt(i);
            }
            System.out.printf("%s = %s%n",command, reversed);

            command = scanner.nextLine();
        }
    }
}
