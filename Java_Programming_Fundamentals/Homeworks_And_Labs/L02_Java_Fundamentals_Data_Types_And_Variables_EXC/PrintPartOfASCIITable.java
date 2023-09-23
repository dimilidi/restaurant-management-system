package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class PrintPartOfASCIITable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int startIndex = Integer.parseInt(scanner.nextLine());
        int endIndex = Integer.parseInt(scanner.nextLine());

        // valid ASCII range (0 to 127)
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (endIndex > 127) {
            endIndex = 127;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            char character = (char) i;
            System.out.print(character + " ");
        }
    }
}
