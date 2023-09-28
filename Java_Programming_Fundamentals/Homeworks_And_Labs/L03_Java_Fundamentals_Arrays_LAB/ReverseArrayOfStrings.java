package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Scanner;
public class ReverseArrayOfStrings {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arr = scanner.nextLine().split(" ");
        String[] reversedArr = new String[arr.length];

        for (int i = 0; i < arr.length ; i++) {
            reversedArr[i] = arr[(arr.length - 1) - i];
            System.out.print(reversedArr[i] + " ");
        }

    }
}
