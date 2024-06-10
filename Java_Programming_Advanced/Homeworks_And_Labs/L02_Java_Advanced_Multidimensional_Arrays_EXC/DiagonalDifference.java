package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class DiagonalDifference {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[size][size];

        fillMatrix(matrix, scanner);

        int sumPrimaryDiagonal = getSumElementsPrimaryDiagonal(matrix);
        int sumSecondaryDiagonal = getSumElementsSecondaryDiagonal(matrix);
        System.out.println(Math.abs(sumPrimaryDiagonal - sumSecondaryDiagonal));

    }

    private static int getSumElementsSecondaryDiagonal(int[][] matrix) {
        int sum = 0;
        // in secondary diagonals col = matrixLength(totalRow Count) - row - 1
        // in secondary diagonals col + row = matrixLength(totalRow Count) - 1
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                int currentElement = matrix[row][col];
                if(col == matrix.length - row - 1) {
                    sum += currentElement;
                }
            }
        }
        return  sum;
    }

    private static int getSumElementsPrimaryDiagonal(int[][] matrix) {
        int sum = 0;
        // in primary diagonal row == col
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                int currentElement = matrix[row][col];
                if(row == col) {
                    sum += currentElement;
                }
            }
        }
        return sum;
    }

    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            int[] input = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = input;
        }
    }
}
