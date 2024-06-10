package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class ThroneConquering_JavaAdvancedRetakeExam15December2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int energy = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());
        char [][] field = new char[rows][];

        field = readCharMatrix(scanner, rows);

        int[]  parisPosition = findParis(field, rows);
        int parisRow = parisPosition[0];
        int parisCol = parisPosition[1];


        while (true) {
            String command = scanner.nextLine();
            String direction = command.split(" ")[0];
            int enemyRow = Integer.parseInt(command.split(" ")[1]);
            int enemyCol = Integer.parseInt(command.split(" ")[2]);

            field[parisRow][parisCol] = '-';
            field[enemyRow][enemyCol] = 'S';

            switch (direction) {
                case "up":
                    if (parisRow - 1 >= 0) {
                        parisRow--;
                    }
                    break;
                case "down":
                    if (parisRow + 1 < field.length) {
                        parisRow++;
                    }
                    break;
                case "left":
                    if (parisCol - 1 >= 0) {
                        parisCol--;
                    }
                    break;
                case "right":
                    if(parisCol + 1 < field.length) {
                        parisCol++;
                    }

                    break;
            }

            energy--;
            if (energy <= 0) {
                parisDead(field, parisRow, parisCol);
                return;
            }

            if (field[parisRow][parisCol] == 'S') {
                energy -= 2;
                if (energy <= 0) {
                    parisDead(field, parisRow, parisCol);
                    return;
                } else {
                    field[parisRow][parisCol] = '-';
                }
            }
            else if (field[parisRow][parisCol] == 'H') {
                field[parisRow][parisCol] = '-';
                System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
                printField(field);
                return;
            }
        }
    }

    private static int[] findParis(char[][] field, int rows) {
        int [] parisPosition = new int[2];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == 'P') {
                    parisPosition[0] = row;
                    parisPosition[1] = col;
                }
            }
        }
        return parisPosition;
    }

    public static void parisDead(char[][] field, int parisRow, int parisCol) {
        field[parisRow][parisCol] = 'X';
        System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
        printField(field);
    }

    private static void printField(char[][] field) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                System.out.print(field[row][col]);
            }
            System.out.println();
        }
    }

    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] field = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            field[row] = rowInput.toCharArray();
        }

        return field;
    }
}
