package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read matrix dimensions M and N
        int m = Integer.parseInt(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());

        // Read the matrix A and matrix B
        char[][] matrixA = readMatrix(scanner, m);
        char[][] matrixB = readMatrix(scanner, m);

        // Find and print the intersection matrix
        printMatrix(matrixA, matrixB);
    }

    private static char[][] readMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            matrix[row] = rowInput.toCharArray();
        }

        return matrix;
    }

    private static void printMatrix( char[][] matrixA, char[][] matrixB) {
        for (int row = 0; row < matrixA.length; row++) {
            for (int col = 0; col < matrixA[row].length; col++) {
                char output = '*';
               if(matrixA[row][col] == matrixB[row][col]) {
                 output = matrixA[row][col];
                }
                System.out.print(output);

            }
            System.out.println();
        }
    }
}
