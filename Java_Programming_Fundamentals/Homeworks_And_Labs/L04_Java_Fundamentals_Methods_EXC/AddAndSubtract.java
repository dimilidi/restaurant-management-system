package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class AddAndSubtract {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num1 = Integer.parseInt(scanner.nextLine());
        int num2 = Integer.parseInt(scanner.nextLine());
        int num3 = Integer.parseInt(scanner.nextLine());

        int sum = sum(num1, num2);
        System.out.println(subtract(sum, num3));
    }


    public static int sum(int numOne, int numTwo) {
        return numOne + numTwo;
    }

    public static int subtract(int sum, int num) {
        return sum - num;
    }
}
