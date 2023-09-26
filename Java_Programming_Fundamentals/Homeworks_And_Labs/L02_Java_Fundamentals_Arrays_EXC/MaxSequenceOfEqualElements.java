package Homeworks_And_Labs.L02_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine();
        String[] inputArray = inputLine.split(" ");

        int[] numbers = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            numbers[i] = Integer.parseInt(inputArray[i]);
        }

        int longestSequenceStart = 0;
        int longestSequenceLength = 1;

        int currentSequenceStart = 0;
        int currentSequenceLength = 1;

        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == numbers[i - 1]) {
                currentSequenceLength++;
                if (currentSequenceLength > longestSequenceLength) {
                    longestSequenceStart = currentSequenceStart;
                    longestSequenceLength = currentSequenceLength;
                }
            } else {
                currentSequenceStart = i;
                currentSequenceLength = 1;
            }
        }

        for (int i = longestSequenceStart; i < longestSequenceStart + longestSequenceLength; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
}
