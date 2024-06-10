package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class FormulaOne_JavaAdvancedRetakeExam18August2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int countCommands = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];

        fillMatrix(scanner, size, matrix);
        //P -> player position
        //F -> final
        //B -> bonus
        //T -> trap

        // initial position of player
        int rowPlayer = findPlayer(matrix, size)[0];
        int colPlayer = findPlayer(matrix, size)[1];

        boolean hasWon = false;
        for (int commandCount = 1; commandCount <= countCommands; commandCount++) {
            String direction = scanner.nextLine();

            int newRow = 0;
            int newCol = 0;
            switch (direction) {
                case "up":
                    //row - 1
                    newRow = checkIsOutside(rowPlayer - 1, size);
                    newCol = checkIsOutside(colPlayer, size);
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = checkIsOutside(rowPlayer - 2, size);
                        newCol = checkIsOutside(colPlayer, size);
                    }
                    break;
                case "down":
                    //row + 1
                    newRow = checkIsOutside(rowPlayer + 1, size);
                    newCol = checkIsOutside(colPlayer, size);
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = checkIsOutside(rowPlayer + 2, size);
                        newCol = checkIsOutside(colPlayer, size);
                    }
                    break;
                case "left":
                    //col - 1
                    newRow = checkIsOutside(rowPlayer, size);
                    newCol = checkIsOutside(colPlayer - 1, size);
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = checkIsOutside(rowPlayer, size);
                        newCol = checkIsOutside(colPlayer - 2, size);
                    }
                    break;
                case "right":
                    //col + 1
                    newRow = checkIsOutside(rowPlayer, size);
                    newCol = checkIsOutside(colPlayer + 1, size);
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = checkIsOutside(rowPlayer, size);
                        newCol = checkIsOutside(colPlayer + 2, size);
                    }
                    break;
            }
            // check for  B, T, F
            if (matrix[newRow][newCol] == 'T') {
                // stay
                newRow = rowPlayer;
                newCol = colPlayer;
            } else {
                // move
                if (matrix[newRow][newCol] == 'F') {
                    hasWon = true;
                }
                matrix[rowPlayer][colPlayer] = '.';
                matrix[newRow][newCol] = 'P';
                rowPlayer = newRow;
                colPlayer = newCol;

            }
        }

        if (hasWon) {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
        }

        printMatrix(matrix);


    }

    private static int[] findPlayer(char[][] matrix, int size) {
        int[] playerPosition = new int[2];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == 'P') {
                    playerPosition[0] = row;
                    playerPosition[1] = col;
                }
            }
        }
        return playerPosition;
    }

    private static int checkIsOutside(int value, int size) {
        if (value < 0) {
            value = size - 1;
        } else if (value >= size ) {
            value = 0;
        }
        return value;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
        }

    }
}
