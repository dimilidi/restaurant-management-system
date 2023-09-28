package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class EqualArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] arr1 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] arr2 = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean isEqual = true;
        int maxLength = Math.max(arr1.length, arr2.length);
        int sum = 0;
        int positionOfDifference = 0;


        for (int i = 0; i < maxLength; i++) {
            if (arr1[i] != arr2[i]) {
                isEqual = false;
                positionOfDifference = i;
                break;
            }
            sum  += arr1[i];
        }

        if(!isEqual){
            System.out.printf("Arrays are not identical. Found difference at %d index.", positionOfDifference);
        } else {
            System.out.printf("Arrays are identical. Sum: %d", sum);
        }

    }
}
