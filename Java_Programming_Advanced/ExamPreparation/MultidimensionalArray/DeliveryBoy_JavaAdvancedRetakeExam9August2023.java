package ExamPreparation.MultidimensionalArray;

import java.util.Arrays;
import java.util.Scanner;

public class DeliveryBoy_JavaAdvancedRetakeExam9August2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] field = readCharMatrix(scanner, rows);
        int[] initialPosition =  findPosition(field, rows, 'B');
        int boyRow = initialPosition[0];
        int boyCol = initialPosition[1];


        while(true) {
            int currentRow = boyRow;
            int currentCol = boyCol;

            String direction = scanner.nextLine();


           switch(direction) {
               case "up":
                   boyRow--;
                   break;
               case "down":
                   boyRow++;
                   break;
               case "left":
                   boyCol--;
                   break;
               case "right":
                   boyCol++;
                   break;
           }


            if(!isInBounds(field, boyRow, boyCol)) {
                field[initialPosition[0]][initialPosition[1]] = ' ';
                System.out.println("The delivery is late. Order is canceled.");
                break;
            } else {
                if (field[boyRow][boyCol] == '*' ) {
                    boyRow = currentRow;
                    boyCol = currentCol;
                } else if(field[boyRow][boyCol] == 'P') {
                    field[boyRow][boyCol] = 'R';
                    System.out.println("Pizza is collected. 10 minutes for delivery.");
                } else if(field[boyRow][boyCol] == 'A' ) {
                    field[boyRow][boyCol] = 'P';
                    System.out.println("Pizza is delivered on time! Next order...");
                    break;
                } else if (field[boyRow][boyCol] == '-' ) {
                    field[boyRow][boyCol] = '.';
                }
            }
        }

        printMatrixFor(field);
    }



    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            matrix[row] = rowInput.toCharArray();
        }

        return matrix;
    }

    private static void printMatrixFor(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

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


    private static boolean isInBounds(char[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
    }
}
