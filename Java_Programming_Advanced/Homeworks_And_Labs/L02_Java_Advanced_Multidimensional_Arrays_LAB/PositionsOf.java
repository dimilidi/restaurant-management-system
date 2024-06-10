package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class PositionsOf {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            for (int col = 0; col < cols; col++) {
                matrix[row][col] = arr[col];
            }
        }

        boolean isFound = false;

        int specialValue = Integer.parseInt(scanner.nextLine());
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                int currentElement = matrix[row][col];
                if(currentElement == specialValue) {
                    System.out.println(row + " " + col);
                    isFound = true;
                }
            }
        }
        if(!isFound) {
            System.out.println("not found");
        }
    }
}
