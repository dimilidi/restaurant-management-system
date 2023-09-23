package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class LeftAndRightSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int sum1 = 0;
        int sum2 = 0;


        for (int i = 0; i < count; i++) {
           int num1 = Integer.parseInt(scanner.nextLine());
           sum1 += num1;
        }

        for (int j = 0; j < count; j++) {
           int num2 = Integer.parseInt(scanner.nextLine());
            sum2 += num2;
        }

        if (sum1 == sum2) {
            System.out.println("Yes, sum = " + sum1);
        } else {
            System.out.println("No, diff = " + Math.abs(sum1 - sum2));
        }
    }
}
