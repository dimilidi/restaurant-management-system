package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class NavyBattle_JavaAdvancedRetakeExam14December2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        char[][] field = new char[size][size];

        fillMatrix(scanner, size, field);

        int[] submarinePosition = findPosition(field, size, 'S');
        int submarineRow = submarinePosition[0];
        int submarineCol = submarinePosition[1];

        int mineCount = 0;
        int cruiserCount = 3;

        while(true) {
            String direction = scanner.nextLine();

            if(field[submarineRow][submarineCol] == 'S') {
                field[submarineRow][submarineCol] = '-';
            }

            switch(direction) {
                case "up":
                    submarineRow--;
                    break;
                case "down":
                    submarineRow++;
                    break;
                case "left":
                    submarineCol--;
                    break;
                case "right":
                    submarineCol++;
                    break;
            }

             if(field[submarineRow][submarineCol] == '*') {
                mineCount++;
                field[submarineRow][submarineCol] = '-';
                if(mineCount == 3) {
                    field[submarineRow][submarineCol] = 'S';
                    System.out.printf("Mission failed, U-9 disappeared! Last known coordinates [%d, %d]!%n", submarineRow, submarineCol);
                    break;
                }
            } else if(field[submarineRow][submarineCol] == 'C') {
                cruiserCount--;
                field[submarineRow][submarineCol] = '-';
                if(cruiserCount == 0) {
                    field[submarineRow][submarineCol] = 'S';
                    System.out.println("Mission accomplished, U-9 has destroyed all battle cruisers of the enemy!");
                    break;
                }
            }
        }

        printMatrixFor(field);
    }

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = line.toCharArray();
        }
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

}
