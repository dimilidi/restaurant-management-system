package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSumOf2X2SubMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] matrix = readMatrix(scanner);

        int[][] maxSubMatrix = new int[2][2];

        int finalSum = Integer.MIN_VALUE;

        for (int row = 0; row < matrix.length - 1; row++) {
            for (int col = 0; col < matrix[row].length - 1; col++) {
                int currentElement = matrix[row][col];
                int right = matrix[row][col + 1];
                int below = matrix[row + 1][col];
                int rightBelow = matrix[row + 1][col + 1];

                int sum = currentElement + right + below + rightBelow;

                if(finalSum < sum){
                    finalSum =sum;
                    maxSubMatrix[0][0] = currentElement;
                    maxSubMatrix[0][1] = right;
                    maxSubMatrix[1][0] = below;
                    maxSubMatrix[1][1] = rightBelow;
                }
            }
        }
        System.out.println(maxSubMatrix[0][0] + " " + maxSubMatrix[0][1]);
        System.out.println(maxSubMatrix[1][0] + " " + maxSubMatrix[1 ][1]);
        System.out.println(finalSum);
    }

    private static int[][] readMatrix(Scanner scanner) {
        int[] dimensions = getArray(scanner);
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            int[] arr = getArray(scanner);
            matrix[row] = arr;
        }
        return matrix;
    }

    private static int[] getArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
