package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class MatrixShuffling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        String[][] matrix = new String[rows][cols];
        fillMatrix(matrix, scanner);

        String command = scanner.nextLine();

        while(!"END".equals(command)){
            String[] commandParts = command.split("\\s+");
            if(validateCommand(command, rows, cols, matrix)) {
                int rowFirstElement = Integer.parseInt(commandParts[1]);
                int colFirstElement = Integer.parseInt(commandParts[2]);
                int rowSecondElement = Integer.parseInt(commandParts[3]);
                int colSecondElement = Integer.parseInt(commandParts[4]);

                String firstElement = matrix[rowFirstElement][colFirstElement];
                String secondElement = matrix[rowSecondElement][colSecondElement];

                // swap
                matrix[rowFirstElement][colFirstElement] = secondElement;
                matrix[rowSecondElement][colSecondElement] = firstElement;

                // print swapped matrix
                printMatrix(matrix);
            } else {
                System.out.println("Invalid input!");
            }
            command = scanner.nextLine();
        }
    }

    private static boolean validateCommand(String command, int rows, int cols, String[][] matrix) {
        String [] commandParts = command.split("\\s+");
        // count commandParts
        if(commandParts.length != 5){
            return false;
        }

        // starts with "swap"
        if(!"swap".equals(commandParts[0])) {
            return false;
        }

        int rowFirstElement = Integer.parseInt(commandParts[1]);
        int colFirstElement = Integer.parseInt(commandParts[2]);
        int rowSecondElement = Integer.parseInt(commandParts[3]);
        int colSecondElement = Integer.parseInt(commandParts[4]);

        // rows and cols are inbounds
        if(!isInBounds(matrix, rowFirstElement, colFirstElement) &&
                !isInBounds(matrix, rowSecondElement, colSecondElement)) {
            return false;
        }


        return true;
    }

    private static boolean isInBounds(String[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length; row++) {
            String[] input = scanner.nextLine().split("\\s+");
            matrix[row] = input;
        }
    }

    private static void printMatrix(String[][] matrix) {
        for (String[] arr : matrix) {
            for (String el : arr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
}
