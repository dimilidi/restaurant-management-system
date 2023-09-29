package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class SmallestOfThreeNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numArr = new int[3];

        for (int i = 0; i < numArr.length; i++) {
            int input = Integer.parseInt(scanner.nextLine());;

            numArr[i] = input;
        }

        System.out.println(findSmallestNumber(numArr));

    }

    public static int findSmallestNumber(int[] numbers) {
        int minNum = Integer.MAX_VALUE;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] < minNum) {
                minNum = numbers[i];
            }
        }
        return minNum;
    }
}
