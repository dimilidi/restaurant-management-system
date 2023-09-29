package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class TopNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = Integer.parseInt(scanner.nextLine());
        for (int i = 1; i < number; i++) {
            if(isTopNumber(i)) {
                System.out.println(i);
            }
        }
    }


    public static boolean isTopNumber(int number) {
        String[] numberToStringArr = String.valueOf(number).split("");
        int[] digitArr = Arrays
                .stream(numberToStringArr)
                .mapToInt(Integer::parseInt)
                .toArray();

        int digitSum = 0;
        boolean isOddDigit = false;

        for (int i = 0; i < digitArr.length; i++) {
            digitSum += digitArr[i];
            if (digitArr[i] % 2 != 0) {
                isOddDigit = true;
            }
        }

        return (isOddDigit && digitSum % 8 == 0);
    }

}
