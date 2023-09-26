package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class TopIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] inputArr = scanner.nextLine().split(" ");
        String result = "";

        for (int i = 0; i < inputArr.length; i++) {
            int currentNumber = Integer.parseInt(inputArr[i]);
            boolean isTopInteger = true;

            for (int j = i + 1; j < inputArr.length; j++) {
                if (currentNumber <= Integer.parseInt(inputArr[j])) {
                    isTopInteger = false;
                    break;
                }
            }

            if (isTopInteger) {
                result += currentNumber + " ";
            }
        }

        System.out.println(result);
    }
}
