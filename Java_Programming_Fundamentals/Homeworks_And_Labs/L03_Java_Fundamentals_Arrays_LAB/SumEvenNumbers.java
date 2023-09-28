package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class SumEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = 0;

        int[] numArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for(int num : numArr){
            if(num % 2 == 0){
                sum += num;
            }
        }

        System.out.println(sum);
    }
}
