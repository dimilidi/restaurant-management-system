package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class StickyFingers_JavaAdvancedRegularExam25June2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(",");
        char[][] field = new char[size][size];
        fillMatrix(scanner, size, field);

        int initialRow = findInitialPosition(field, size)[0];
        int initialCol = findInitialPosition(field, size)[1];
        int totalMoney = 0;

      /*  + - regular position on the field.
        $ - house
        D - Dillinger position
        P – police*/

        for (int i = 0; i < directions.length; i++) {

            switch (directions[i]) {
                case "up":
                    if (initialRow - 1 < 0) {
                        System.out.println("You cannot leave the town, there is police outside!");
                    } else {
                        field[initialRow][initialCol] = '+';
                        initialRow--;
                    }
                    break;
                case "down":
                    if (initialRow + 1 >= size) {
                        System.out.println("You cannot leave the town, there is police outside!");
                    } else {
                        field[initialRow][initialCol] = '+';
                        initialRow++;
                    }
                    break;
                case "left":
                    if (initialCol - 1 < 0) {
                        System.out.println("You cannot leave the town, there is police outside!");
                    } else {
                        field[initialRow][initialCol] = '+';
                        initialCol--;
                    }
                    break;
                case "right":
                    if (initialCol + 1 >= size) {
                        System.out.println("You cannot leave the town, there is police outside!");
                    } else {
                        field[initialRow][initialCol] = '+';
                        initialCol++;
                    }
                    break;
            }

            if (field[initialRow][initialCol] == '$') {
                int money = initialRow * initialCol;
                field[initialRow][initialCol] = '+';
                totalMoney += money;
                System.out.println("You successfully stole " + money + "$.");
                field[initialRow][initialCol] = 'D';
            } else if (field[initialRow][initialCol] == 'P') {
                field[initialRow][initialCol] = '#';
                System.out.printf("You got caught with %d$, and you are going to jail.%n", totalMoney);
                printMatrix(field);
                return;
            } else if (field[initialRow][initialCol] == '+') {
                field[initialRow][initialCol] = 'D';
            }
        }

        System.out.printf("Your last theft has finished successfully with %d$ in your pocket.%n", totalMoney);
        printMatrix(field);
    }


    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = line.toCharArray();
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    private static int[] findInitialPosition(char[][] matrix, int size) {
        int[] initialPosition = new int[2];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == 'D') {
                    initialPosition[0] = row;
                    initialPosition[1] = col;
                    break;
                }
            }
        }
        return initialPosition;
    }


    private static boolean isInBounds(char[][] matrix, int r, int c) {
        return r >= 0 && r < matrix.length
                && c >= 0 && c < matrix[r].length;
    }
}
