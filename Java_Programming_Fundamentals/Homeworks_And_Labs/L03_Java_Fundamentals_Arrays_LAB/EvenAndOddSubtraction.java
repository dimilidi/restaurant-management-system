package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class EvenAndOddSubtraction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int evenSum = 0;
        int oddSum = 0;

        for(int num : numbers){
            if(num % 2 == 0){
                evenSum += num;
            } else {
                oddSum += num;
            }
        }

        int difference = evenSum - oddSum;

        System.out.println(difference);

    }
}
