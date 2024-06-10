package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split(", "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];
        matrix =  getMatrix(scanner, rows);
        printResult(rows, cols,  getSum(matrix));
    }

    private static int getSum(int[][] matrix) {
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
              sum += matrix[row][col];
            }
        }
        return sum;
    }

    private static int[][] getMatrix(Scanner scanner, int rows) {
        int[][] matrix = new int[rows][];

        for (int row = 0; row < rows; row++) {
            int[] input = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
                matrix[row] = input;
        }
        return matrix;
    }

    public static void printResult(int rows, int cols, int sum) {
        System.out.println(rows);
        System.out.println(cols);
        System.out.println(sum);
    }
}
