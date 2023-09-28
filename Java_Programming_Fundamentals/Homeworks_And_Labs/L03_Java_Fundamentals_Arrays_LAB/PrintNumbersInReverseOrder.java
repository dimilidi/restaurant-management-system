package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class PrintNumbersInReverseOrder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countNum = Integer.parseInt(scanner.nextLine());
        int[] numbers = new int[countNum];

        for (int i = 0; i <= countNum - 1 ; i++) {
            int number = Integer.parseInt(scanner.nextLine());
            numbers[i] = number;
        }

        for (int i = numbers.length - 1; i >=0 ; i--) {
            System.out.print(numbers[i] + " ");
        }
    }
}
