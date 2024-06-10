package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrixWithWhileLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix =  readMatrix(scanner);

        int row = 0;
        int col = 0;

        while (row < matrix.length && col < matrix.length) {
            System.out.print(matrix[row][col] + " ");
            row++;
            col++;
        }
        System.out.println();

         row = matrix.length - 1;
         col = 0;

        while (row >= 0 && col < matrix.length) {
            System.out.print(matrix[row][col] + " ");
            row--;
            col++;
        }
    }
    private static int[][] readMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            int[] arr = getArray(scanner);
            matrix[row] = arr;
        }
        return matrix;
    }

    private static int[] getArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
