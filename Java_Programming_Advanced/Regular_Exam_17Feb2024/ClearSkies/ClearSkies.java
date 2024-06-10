package Regular_Exam_17Feb2024.ClearSkies;

import java.util.Scanner;

public class ClearSkies {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read the size of the matrix and initialize it
        int size = Integer.parseInt(scanner.nextLine());
        char[][] field = new char[size][size];

        // Step 2: Fill the matrix with the provided values
        fillMatrix(scanner, size, field);

        // Step 3: Find the initial position of the jetfighter marked with 'J'
        int[] jetPosition = findPosition(field, size, 'J');
        int jetRow = jetPosition[0];
        int jetCol = jetPosition[1];

        // Step 4: Iterate through the directions given for the jetfighter
        int armor = 300;
        int enemyCount = 4;

        move(scanner, field, jetRow, jetCol, armor, enemyCount);

        // Step 8: Print the final state of the matrix
        printMatrix(field);
    }

    private static void move(Scanner scanner, char[][] field, int jetRow, int jetCol, int armor, int enemyCount) {
        while (armor > 0 && enemyCount > 0) {
            field[jetRow][jetCol] = '-';
            // Step 5: Update the position of the jetfighter according to the direction
            String direction = scanner.nextLine();
            switch (direction) {
                case "up":
                    jetRow--;
                    break;
                case "down":
                    jetRow++;
                    break;
                case "left":
                    jetCol--;
                    break;
                case "right":
                    jetCol++;
                    break;
            }

            // Step 6: Handle encounters with enemy aircraft ('E') and repair positions ('R')
            char currentCell = field[jetRow][jetCol];
            if (currentCell == 'E') {
                if (armor == 100) {
                    System.out.printf("Mission failed, your jetfighter was shot down! Last coordinates [%d, %d]!\n", jetRow, jetCol);
                    field[jetRow][jetCol] = 'J';
                    break;
                } else {
                    armor -= 100;
                    enemyCount--;
                    field[jetRow][jetCol] = '-';
                }
            } else if (currentCell == 'R') {
                armor = 300;
                field[jetRow][jetCol] = '-';
            }

            // Step 7: Check for mission success condition
            if (enemyCount == 0) {
                System.out.println("Mission accomplished, you neutralized the aerial threat!");
                field[jetRow][jetCol] = 'J';
                break;
            }
        }
    }

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
        }
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private static int[] findPosition(char[][] matrix, int size, char symbol) {
        int[] position = new int[2];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (matrix[row][col] == symbol) {
                    position[0] = row;
                    position[1] = col;
                    return position;
                }
            }
        }
        return position;
    }
}
