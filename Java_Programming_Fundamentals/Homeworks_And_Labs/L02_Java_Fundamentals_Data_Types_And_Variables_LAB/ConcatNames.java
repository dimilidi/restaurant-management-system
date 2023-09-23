package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class ConcatNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String name1 = scanner.nextLine();
        String name2 = scanner.nextLine();
        String delimiter = scanner.nextLine();
        String namesWithDelimiter = String.format("%s%s%s", name1, delimiter, name2);

        System.out.println(namesWithDelimiter);
    }
}
