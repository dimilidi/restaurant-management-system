package Homeworks_And_Labs.L02_Java_Fundamentals_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class MagicSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] intArr = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int number = Integer.parseInt(scanner.nextLine());

        int sum = 0;

        for (int i = 0; i < intArr.length; i++) {
            int currNumber = intArr[i];

            for (int j = i + 1; j < intArr.length; j++) {
                int nextNumber = intArr[j];
                sum = currNumber + nextNumber;

                if (sum == number) {
                    System.out.println(currNumber + " " + nextNumber);
                }
            }

        }

    }
}
