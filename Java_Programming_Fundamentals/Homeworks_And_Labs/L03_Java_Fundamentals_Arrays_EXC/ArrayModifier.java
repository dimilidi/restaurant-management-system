package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class ArrayModifier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int[] arr = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();

        while (!input.equals("end")) {
            String[] commandStr = input.split(" ");

            switch (commandStr[0]) {
                case "swap":
                    int index1 = Integer.parseInt(commandStr[1]);
                    int index2 = Integer.parseInt(commandStr[2]);
                    int temp = arr[index1];
                    arr[index1] = arr[index2];
                    arr[index2] = temp;
                    break;
                case "multiply":
                    int multiplyIndex1 = Integer.parseInt(commandStr[1]);
                    int multiplyIndex2 = Integer.parseInt(commandStr[2]);
                    arr[multiplyIndex1] *= arr[multiplyIndex2];
                    break;
                case "decrease":
                    for (int i = 0; i < arr.length; i++) {
                        arr[i]--;
                    }
                    break;
            }

            input = scanner.nextLine();
        }

        for (int i = 0; i < arr.length; i++) {
            if (i == arr.length - 1) {
                System.out.print(arr[i]);
            } else {
                System.out.print(arr[i] + ", ");
            }
        }
    }
}
