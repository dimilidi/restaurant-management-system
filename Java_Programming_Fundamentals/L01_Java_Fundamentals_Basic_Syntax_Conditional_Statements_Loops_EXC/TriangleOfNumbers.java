package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class TriangleOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= number; i++) {
            System.out.println();

            for (int j = i ; j > 0; j--) {
                System.out.print(i + " ");
            }
        }
    }
}
