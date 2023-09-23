package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class MultiplicationTable2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        int multiplier = Integer.parseInt(scanner.nextLine());

        if(multiplier > 10) {
            System.out.printf("%d X %d = %d%n", num, multiplier, num * multiplier);
        }  else {
            for (int i = multiplier; i <= 10; i++) {
                int product = num * i;
                System.out.printf("%d X %d = %d%n", num, i, product);
            }
        }
        
    }
}
