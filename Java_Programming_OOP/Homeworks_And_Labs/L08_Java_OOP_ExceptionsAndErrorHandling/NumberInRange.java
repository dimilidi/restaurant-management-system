package Homeworks_And_Labs.L08_Java_OOP_ExceptionsAndErrorHandling;

import java.util.Arrays;
import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int begin = range[0];
        int end = range[1];


        System.out.printf("Range: [%d...%d]%n", begin, end);

        int validNumber = validateNumberInRange(scanner, begin, end);
        System.out.printf("Valid number: %d%n", validNumber);
    }

    public static int validateNumberInRange(Scanner scanner, int startRange, int endRange) {
        while (true) {
            String input = scanner.nextLine();
            try {
                int num = Integer.parseInt(input);
                if (num >= startRange && num <= endRange) {
                    return num;
                } else {
                    System.out.printf("Invalid number: %d%n", num);
                }
            } catch (NumberFormatException e) {
                System.out.printf("Invalid number: %s%n", input);
            }
        }
    }
}