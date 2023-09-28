package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Scanner;

public class ReversedArrayOfString2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] arr = scanner.nextLine().split(" ");

        int j = arr.length - 1;

        for (int i = 0; i < arr.length /2 ; i++) {
            String leftElement = arr[i];
            String rightElement = arr[j];

            arr[i] = rightElement;
            arr[j] = leftElement;

            j -= 1;
        }

        String stringOfArray = String.join(" ", arr);

        System.out.println(stringOfArray);
    }
}
