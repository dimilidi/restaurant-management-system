package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_More_Exc;

import java.util.Scanner;

public class SortNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());;

        int num2 = Integer.parseInt(scanner.nextLine());

        int num3 = Integer.parseInt(scanner.nextLine());

        // Find the maximum number
        int max = num1;
        if (num2 > max) {
            max = num2;
        }
        if (num3 > max) {
            max = num3;
        }

        // Find the minimum number
        int min = num1;
        if (num2 < min) {
            min = num2;
        }
        if (num3 < min) {
            min = num3;
        }

        // Calculate the middle number
        int middle = num1 + num2 + num3 - max - min;

        // Print the numbers in descending order
        System.out.println(max);
        System.out.println(middle);
        System.out.println(min);
    }

}
