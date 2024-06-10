package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class Armory_JavaAdvancedRetakeExam13April2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] armory = new char[size][size];
        fillMatrix(scanner, size, armory);

        int[] officerPosition = findPosition(armory, 'A');
        int officerRow = officerPosition[0];
        int officerCol = officerPosition[1];

        int coins = exploreArmory(scanner, armory, officerRow, officerCol);

        printResult(coins, armory);
    }

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = line.toCharArray();
        }
    }

    private static int[] findPosition(char[][] matrix, char symbol) {
        int[] playerPosition = new int[2];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == symbol) {
                    playerPosition[0] = row;
                    playerPosition[1] = col;
                    return playerPosition;
                }
            }
        }
        return playerPosition;
    }

    private static int exploreArmory(Scanner scanner, char[][] armory, int officerRow, int officerCol) {
        int coins = 0;
        boolean isOutOfBounds = false;

        while (coins < 65 && !isOutOfBounds) {
            String direction = scanner.nextLine();

            armory[officerRow][officerCol] = '-';

            switch (direction) {
                case "up":
                    officerRow--;
                    break;
                case "down":
                    officerRow++;
                    break;
                case "left":
                    officerCol--;
                    break;
                case "right":
                    officerCol++;
                    break;
            }

            if (isInBounds(armory, officerRow, officerCol)) {
                char currentSymbol = armory[officerRow][officerCol];

                if (Character.isDigit(currentSymbol)) {
                    int coin = Character.getNumericValue(currentSymbol);
                    coins += coin;
                    armory[officerRow][officerCol] = '-';
                } else if (currentSymbol == 'M') {
                    armory[officerRow][officerCol] = '-';
                    int[] newPosition = findPosition(armory, 'M');
                    officerRow = newPosition[0];
                    officerCol = newPosition[1];
                    armory[officerRow][officerCol] = 'A';
                }
                armory[officerRow][officerCol] = '-';
            } else {
                isOutOfBounds = true;
            }
        }

        if (isInBounds(armory, officerRow, officerCol)) {
            armory[officerRow][officerCol] = 'A';
        }

        return coins;
    }

    private static boolean isInBounds(char[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length && c >= 0 && c < matrix[r].length;
    }

    private static void printResult(int coins, char[][] armory) {
        if (coins >= 65) {
            System.out.println("Very nice swords, I will come back for more!");
        } else {
            System.out.println("I do not need more swords!");
        }
        System.out.printf("The king paid %d gold coins.\n", coins);
        printMatrixFor(armory);
    }

    private static void printMatrixFor(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
}
