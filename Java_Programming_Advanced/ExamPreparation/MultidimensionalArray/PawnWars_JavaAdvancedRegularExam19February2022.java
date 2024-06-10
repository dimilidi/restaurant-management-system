package ExamPreparation.MultidimensionalArray;

import java.util.Map;
import java.util.Scanner;

public class PawnWars_JavaAdvancedRegularExam19February2022 {
    static Map<Integer, Integer> rowMap = Map.of( 0,8,
            1,7,
            2,6,
            3,5,
            4,4,
            5,3,
            6,2,
            7,1);

    static   Map<Integer, Character> colMap = Map.of(0,'a',
            1,'b',
            2,'c',
            3,'d',
            4,'e',
            5,'f',
            6,'g',
            7,'h');
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = readCharMatrix(scanner, 8);

        int[] positionWhitePlayer = findPlayer(board, 8, 'w');
        int rowWhite = positionWhitePlayer[0];
        int colWhite = positionWhitePlayer[1];
        int[] positionBlackPlayer = findPlayer(board, 8, 'b');
        int rowBlack = positionBlackPlayer[0];
        int colBlack = positionBlackPlayer[1];
        boolean isCapture = false;
        int turn = 0;


        while(rowWhite != 0  &&  rowBlack != 7) {
            if(rowWhite - 1 == rowBlack && (colWhite + 1 == colBlack || colWhite - 1 == colBlack)) {
                isCapture = true;
                board[rowBlack][colBlack] = 'w';
                board[rowWhite][colWhite] = '-';
                break;
                }
            // Move White player
            board[rowWhite][colWhite] = '-';
            rowWhite--;
            board[rowWhite][colWhite] = 'w';
            turn++;


            if(rowBlack + 1 == rowWhite && (rowBlack + 1 == colWhite || colBlack - 1 == colWhite)) {
                isCapture = true;
                board[rowBlack][colBlack] = 'b';
                board[rowWhite][colWhite] = '-';
                break;
            }
            // Move Black player
            board[rowBlack][colBlack] = '-';
            rowBlack++;
            board[rowBlack][colBlack] = 'b';
            turn++;
        }

        // Output
        printOutput(rowWhite, colWhite, rowBlack, colBlack, isCapture, turn);

    }

    private static void printOutput(int rowWhite, int colWhite, int rowBlack, int colBlack, boolean isCapture, int turn) {
        if(isCapture) {
            if(turn % 2 == 0) {
                System.out.printf("Game over! White capture on %c%d.", colMap.get(colBlack), rowMap.get(rowBlack));
            } else {
                System.out.printf("Game over! Black capture on %c%d.", colMap.get(colWhite), rowMap.get(rowWhite));
            }

        }

        if(!isCapture) {
            if(rowWhite == 0) {
                System.out.printf("Game over! White pawn is promoted to a queen at %c%d.", colMap.get(colWhite), rowMap.get(rowWhite));
            } else if (rowBlack == 7) {
                System.out.printf("Game over! Black pawn is promoted to a queen at %c%d.", colMap.get(colBlack), rowMap.get(rowBlack));
            }
        }
    }

    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] matrix = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            matrix[row] = rowInput.toCharArray();
        }

        return matrix;
    }

    private static void printBoard(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char el : arr) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    private static int[] findPlayer(char[][] matrix, int size, char symbol) {
        int[] playerPosition = new int[2];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
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
