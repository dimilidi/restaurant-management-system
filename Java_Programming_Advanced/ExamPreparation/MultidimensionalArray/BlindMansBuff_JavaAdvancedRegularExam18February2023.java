package ExamPreparation.MultidimensionalArray;

import java.util.Arrays;
import java.util.Scanner;

public class BlindMansBuff_JavaAdvancedRegularExam18February2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int rows = dimensions[0];
        int cols = dimensions[1];

        char[][] playgrounds = readCharMatrix(scanner, rows);

        int[] position = findPosition(playgrounds, rows, 'B');
        int myRow = position[0];
        int myCol = position[1];

        int touchedOpponents = 0;
        int moves = 0;

        String command = scanner.nextLine();

        while(!"Finish".equals(command) && touchedOpponents < 3) {
            int currentRow = myRow;
            int currentCol = myCol;

            playgrounds[myRow][myCol] = '-';

            switch (command) {
                case "up":
                    myRow--;
                    break;
                case "down":
                    myRow++;
                    break;
                case "left":
                    myCol--;
                    break;
                case "right":
                    myCol++;
                    break;
            }

            if(isInBounds(playgrounds, myRow, myCol)) {
                if(playgrounds[myRow][myCol] == 'O') {
                    // obstacle => do not move, wait for next command
                    myRow = currentRow;
                    myCol = currentCol;
                    command = scanner.nextLine();
                    continue;
                } else if(playgrounds[myRow][myCol] == '-') {
                    moves++;
                } else if(playgrounds[myRow][myCol] == 'P') {
                    touchedOpponents++;
                    moves++;
                    playgrounds[myRow][myCol] = '-';
                    if(touchedOpponents == 3) {
                        break;
                    }
                }
            } else {
                // out of bounds  => do not move
                myRow = currentRow;
                myCol = currentCol;
            }

            command = scanner.nextLine();
        }

        System.out.println("Game over!");
        System.out.printf("Touched opponents: %d ", touchedOpponents);
        System.out.printf("Moves made: %d", moves);
    }

    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = rowInput.toCharArray();
        }

        return matrix;
    }

    private static void printMatrixFor(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col] + " ");
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
