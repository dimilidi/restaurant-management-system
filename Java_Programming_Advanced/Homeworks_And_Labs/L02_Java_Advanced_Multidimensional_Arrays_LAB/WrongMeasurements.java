package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class WrongMeasurements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] matrix = readMatrix(scanner);
        int[] arr = getArray(scanner);
        int wrongValue = matrix[arr[0]][arr[1]];

        List<int[]> correctedValues = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if(matrix[row][col] == wrongValue) {
                    int actualValue = 0;
                    // Top
                    if(isInBounds(matrix,row - 1,col) && matrix[row - 1][col] != wrongValue) {
                        actualValue += matrix[row - 1][col];
                    }
                    // Left
                    if(isInBounds( matrix,row,col - 1) && matrix[row][col - 1] != wrongValue) {
                        actualValue += matrix[row][col - 1];
                    }
                    // Bottom
                    if(isInBounds(matrix, row + 1, col) && matrix[row + 1][col] != wrongValue){
                        actualValue += matrix[row + 1][col];
                    }
                    // Right
                    if(isInBounds(matrix, row, col + 1) && matrix[row][col + 1] != wrongValue) {
                        actualValue += matrix[row][col + 1];
                    }

                    int[] parameters = new int[3];
                    parameters[0] = row;
                    parameters[1] = col;
                    parameters[2] = actualValue;

                    correctedValues.add(parameters);

                }
            }
        }

        for (int[] params : correctedValues) {
            matrix[params[0]][params[1]] = params[2];
        }
        
        printMatrix(matrix);

    }

    private static void printMatrix(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int el : arr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
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
