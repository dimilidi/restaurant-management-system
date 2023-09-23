package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class SumOfOddNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countOddNumbers = Integer.parseInt(scanner.nextLine());
        int counter = 0;
        int sum = 0;

        for(int i = 1; counter < countOddNumbers; i ++) {
            if(i % 2 != 0) {
                System.out.println(i);
                sum += i;
                counter++;
            }
        }
        System.out.printf("Sum: %d", sum);
    }

}

