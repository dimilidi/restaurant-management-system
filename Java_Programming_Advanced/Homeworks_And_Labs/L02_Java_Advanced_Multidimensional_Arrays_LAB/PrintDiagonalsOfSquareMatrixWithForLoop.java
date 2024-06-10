package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class PrintDiagonalsOfSquareMatrixWithForLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix =  readMatrix(scanner);

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i][i] + " ");
        }
        System.out.println();

        int lastRowIndex = matrix.length - 1;

        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[lastRowIndex - i][i] + " ");
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
