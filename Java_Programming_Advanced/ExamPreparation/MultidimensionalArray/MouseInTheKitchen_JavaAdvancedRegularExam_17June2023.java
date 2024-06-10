package ExamPreparation.MultidimensionalArray;

import java.util.Arrays;
import java.util.Scanner;

public class MouseInTheKitchen_JavaAdvancedRegularExam_17June2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] dimensions = readArray(scanner);
        int rows = dimensions[0];
        int cols = dimensions[1];
        char[][] cupboard = readCharMatrix(scanner, rows);

        int[] mousePosition = findMousePosition(cupboard);
        int[] finalPosition = new int[2];

        int[] cheese = {countCheese(cupboard, mousePosition)};

        boolean isLast[] = {false};
        boolean[] isTrapped = {false};
        boolean[] isOutOfBounds = {false};


        String command = scanner.nextLine();


            while (!"danger".equals(command)) {
                if (isOutOfBounds[0] || isTrapped[0] || cheese[0] == 0 || isLast[0] ){
                    break;
                }

                if(cupboard[mousePosition[0]][mousePosition[1]] == '@') {
                    cupboard[mousePosition[0]][mousePosition[1]] = '*';
                }



                switch (command) {
                    case "up":
                        tryMove(mousePosition, -1, 0, cupboard, finalPosition, isLast, isOutOfBounds, isTrapped, cheese);
                        break;
                    case "down":
                        tryMove(mousePosition, 1, 0, cupboard, finalPosition, isLast, isOutOfBounds, isTrapped, cheese);
                        break;
                    case "left":
                        tryMove(mousePosition, 0, -1, cupboard, finalPosition, isLast, isOutOfBounds, isTrapped, cheese);
                        break;
                    case "right":
                        tryMove(mousePosition, 0, 1, cupboard, finalPosition, isLast, isOutOfBounds, isTrapped, cheese);
                        break;
                }
                command = scanner.nextLine();

            }



        handleOutcome(cupboard, finalPosition, isTrapped[0], isOutOfBounds[0], cheese[0]);

        printMatrix(cupboard);
    }

    private static int[] findMousePosition(char[][] cupboard) {
        int[] mousePosition = new int[2];
        for (int row = 0; row < cupboard.length; row++) {
            for (int col = 0; col < cupboard[row].length; col++) {
                if ('M' == (cupboard[row][col])) {
                    mousePosition[0] = row;
                    mousePosition[1] = col;
                }
            }
        }
        return mousePosition;
    }

    private static int countCheese(char[][] cupboard, int[] mousePosition) {
        int[] cheese = {0};
        for (int row = 0; row < cupboard.length; row++) {
            for (int col = 0; col < cupboard[row].length; col++) {
                if (isInBounds(cupboard, mousePosition[0], mousePosition[1])) {
                    if (cupboard[row][col] == 'C') {
                        cheese[0]++;
                    }
                }
            }
        }
        return cheese[0];
    }


    private static void tryMove(int[] mousePosition, int rowChange, int colChange, char[][] cupboard, int[] finalPosition, boolean[] isLast, boolean[] isOutOfBounds, boolean[] isTrapped, int[] cheese) {
        int newRow = mousePosition[0] + rowChange;
        int newCol = mousePosition[1] + colChange;

        if (isInBounds(cupboard, newRow, newCol)) {

            if (cupboard[newRow][newCol] == 'T') {
                isTrapped[0] = true;
                finalPosition[0] = newRow;
                finalPosition[1] = newCol;
                cupboard[finalPosition[0]][finalPosition[1]] = 'M';
            }

            if  (cupboard[newRow][newCol] == 'C') {
                cheese[0]--;

                if (cheese[0] == 0) {
                    isLast[0] = true;
                    finalPosition[0] = newRow;
                    finalPosition[1] = newCol;
                    cupboard[finalPosition[0]][finalPosition[1]] = 'M';
                }
            }


            if (cupboard[newRow][newCol] != '@' ) {
                cupboard[mousePosition[0]][mousePosition[1]] = '*';
                mousePosition[0] = newRow;
                mousePosition[1] = newCol;
            }


        } else {
            isLast[0] = true;
            isOutOfBounds[0] = true;
            cupboard[mousePosition[0]][mousePosition[1]] = 'M';
           // cupboard[finalPosition[0]][finalPosition[1]] = 'M';
             finalPosition[0]= mousePosition[0];
             finalPosition[1] =  mousePosition[1];
        }
    }



    private static void handleOutcome(char[][] cupboard, int[] finalPosition, boolean isTrapped, boolean isOutOfBounds, int cheese) {
        if (cheese == 0) {
            System.out.println("Happy mouse! All the cheese is eaten, good night!");
        } else if (isTrapped) {
            System.out.println("Mouse is trapped!");
        } else if (isOutOfBounds) {
            System.out.println("No more cheese for tonight!");
        } else if (cheese > 0) {
            System.out.println("Mouse will come back later!");
        }
    }

    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split(","))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static char[][] readCharMatrix(Scanner scanner, int rows) {
        char[][] cupboard = new char[rows][];

        for (int row = 0; row < rows; row++) {
            String rowInput = scanner.nextLine();
            cupboard[row] = rowInput.toCharArray();
        }

        return cupboard;
    }

    private static void printMatrix(char[][] cupboard) {
        for (char[] arr : cupboard) {
            for (char el : arr) {
                System.out.print(el);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(char[][] cupboard, int r, int c) {
        return r >= 0 && r < cupboard.length
                && c >= 0 && c < cupboard[r].length;
    }
}

