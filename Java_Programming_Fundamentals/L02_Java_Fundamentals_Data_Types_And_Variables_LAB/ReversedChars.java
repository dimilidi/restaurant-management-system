package L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class ReversedChars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String char1 = scanner.nextLine();
        String char2 = scanner.nextLine();
        String char3 = scanner.nextLine();

        String reversedString = String.format("%s %s %s", char3, char2, char1);
        System.out.println(reversedString);
    }
}
