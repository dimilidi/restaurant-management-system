package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class CondenseArrayOfNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] temp = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            temp[i] = numbers[i];
        }

        for (int i = 0; i < numbers.length - 1; i++) {
            int[] currArr = new int[temp.length - 1] ;

            for (int j = 0; j <= currArr.length - 1; j++) {
                currArr[j] = temp[j] + temp[j+1];
            }

            temp = currArr;
        }

        System.out.println(temp[0]);

    }
}
