package ExamPreparation.MultidimensionalArray;

import java.util.Scanner;

public class TheSquirrel_JavaAdvancedRetakeExam12April2023 {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int hazelnuts = 0;
            int size = Integer.parseInt(scanner.nextLine());
            String[] directions = scanner.nextLine().split(", ");
            char[][] field = new char[size][size];

            fillMatrix(scanner, size, field);

            int[] squirrelPosition = findInitialPosition(field, size);
            int squirrelRow = findInitialPosition(field, size)[0];
            int squirrelCol = squirrelPosition[1];
            boolean isTrapped = false;
            boolean isOut = false;

                for (String direction : directions) {
                    field[squirrelRow][squirrelCol] = '*';


                    switch(direction) {
                        case "up":
                            squirrelRow--;
                            break;
                        case "down":
                            squirrelRow++;
                            break;
                        case "left":
                            squirrelCol--;
                            break;
                        case "right":
                            squirrelCol++;
                            break;
                    }

                    if(!isInBounds(field, squirrelRow, squirrelCol)) {
                        isOut = true;
                        break;
                    } else if(field[squirrelRow][squirrelCol] == 'h') {
                        hazelnuts++;
                        if(hazelnuts == 3) {
                            field[squirrelRow][squirrelCol] = 's';
                            break;
                        }
                    } else if(field[squirrelRow][squirrelCol] == 't') {
                        isTrapped = true;
                        break;
                    }
                }


            if(isOut) {
                System.out.println("The squirrel is out of the field.");
            } else if (isTrapped) {
                System.out.println("Unfortunately, the squirrel stepped on a trap...");
            } else if (hazelnuts < 3) {
                System.out.println("There are more hazelnuts to collect.");
            } else if (hazelnuts == 3){
                System.out.println("Good job! You have collected all hazelnuts!");
            }

            System.out.printf("Hazelnuts collected: %d", hazelnuts);

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
                if (currentElement == 's') {
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
