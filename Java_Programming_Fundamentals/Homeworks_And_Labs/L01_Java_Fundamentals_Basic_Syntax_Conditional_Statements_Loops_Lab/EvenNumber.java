package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class EvenNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        boolean isEven = false;


        while (!isEven) {
            if(num % 2 == 0) {
                isEven = true;
                break;
            }
            System.out.println("Please write an even number.");
            isEven = false;
            num = Integer.parseInt(scanner.nextLine());
        }

        if(isEven) {
            System.out.println("The number is: " + Math.abs(num));
        }
    }
}
