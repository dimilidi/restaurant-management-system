package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class EqualSums {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArr = scanner.nextLine().split(" ");
        int[] numbers = new int[inputArr.length];

        for (int i = 0; i < inputArr.length; i++) {
            numbers[i] = Integer.parseInt(inputArr[i]);
        }

        for (int i = 0; i < numbers.length; i++) {
            int leftSum = 0;
            int rightSum = 0;

            // Calculate the sum of elements on the left
            for (int j = 0; j < i; j++) {
                leftSum += numbers[j];
            }

            // Calculate the sum of elements on the right
            for (int j = i + 1; j < numbers.length; j++) {
                rightSum += numbers[j];
            }

            if (leftSum == rightSum) {
                System.out.println(i);
                return;
            }
        }

        System.out.println("no");
    }

}
