package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class Utils {
    public static void main(String[] args) {

    }

    // READ

    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            matrix[row] = rowInput.toCharArray();
        }

        return matrix;
    }

    private static int[][] readSquareMatrix(Scanner scanner) {
        int size = Integer.parseInt(scanner.nextLine());

        int[][] matrix = new int[size][size];

        for (int row = 0; row < size; row++) {
            int[] arr = readArray(scanner);
            matrix[row] = arr;
        }
        return matrix;
    }

    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static int[][] readIntMatrix(Scanner scanner, int rows) {
        int[][] matrix = new int[rows][];

        for (int row = 0; row < rows; row++) {
            int[] input = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = input;
        }
        return matrix;
    }

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = line.toCharArray();
        }
    }
    private static void fillIntMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            int[] input = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
            matrix[row] = input;
        }
    }

    private static void fillStringMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            matrix[row] = input;
        }
    }

    // PRINT
    private static void printMatrixForEach(int[][] matrix) {
        for (int[] arr : matrix) {
            for (int el : arr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }

    private static void printMatrixFor(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    // VALIDATE
    private static boolean isInBounds(int[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
    }

    // FIND POSITION
    private static int[] findPosition(char[][] matrix, int rows, char symbol) {
        int[] playerPosition = new int[2];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == symbol) {
                    playerPosition[0] = row;
                    playerPosition[1] = col;
                }
            }
        }
        return playerPosition;
    }


}
