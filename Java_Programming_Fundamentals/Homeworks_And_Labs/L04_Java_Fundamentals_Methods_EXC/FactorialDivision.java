package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class FactorialDivision {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        double factorial1 = getFactorial(num1);
        double factorial2 = getFactorial(num2);

        divide(factorial1, factorial2);
    }


    public static double getFactorial(int number){
        double factorial = number;

        for (int i = number - 1; i > 0; i--) {
            //System.out.println( factorial + "* " + i);
            factorial *=  i;
        }
        return factorial;
    }

    public static void divide(double num1, double num2) {
        System.out.printf("%.2f", num1 / num2);
    }
}
