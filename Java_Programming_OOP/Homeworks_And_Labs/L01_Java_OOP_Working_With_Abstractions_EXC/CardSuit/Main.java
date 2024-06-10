package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.CardSuit;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String enumType = scanner.nextLine();
        CardSuit[] values = CardSuit.values();

        printEnumsNamesAndRanks(values, enumType);

    }
    public static <T extends Enum<T>> void printEnumsNamesAndRanks(T[] values, String enumType) {
        System.out.println(enumType + ":");

        for (T value : values) {
            System.out.printf("Ordinal value: %d; Name value: %s%n",  value.ordinal(), value );
        }
    }
}
