package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.JediGalaxi;

import java.util.Arrays;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] dimensions = readPosition(scanner.nextLine());
        int rows = dimensions[0];
        int cols = dimensions[1];

        int[][] matrix = new int[rows][cols];

        fillField(rows, cols, matrix);

        String command = scanner.nextLine();
        long totalStars = 0;
        while (!command.equals("Let the Force be with you")) {
            int[] evilPosition = readPosition(scanner.nextLine());
            int rowEvil = evilPosition[0];
            int colEvil = evilPosition[1];

            moveEvil(matrix, rowEvil, colEvil);

            int[] jediPosition = readPosition(command);
            int rowJedi = jediPosition[0];
            int colJedi = jediPosition[1];

            int collectedStars = getCollectedStars(matrix, rowJedi, colJedi);
            totalStars += collectedStars;

            command = scanner.nextLine();
        }

        System.out.println(totalStars);


    }

    private static int getCollectedStars(int[][] matrix, int rowJedi, int colJedi) {
        int countStars = 0;
        while (rowJedi >= 0 && colJedi < matrix[1].length) {
            if (rowJedi < matrix.length && colJedi >= 0 && colJedi < matrix[0].length) {
                countStars += matrix[rowJedi][colJedi];
            }

            colJedi++;
            rowJedi--;
        }
        return countStars;
    }

    private static void moveEvil(int[][] matrix, int rowEvil, int colEvil) {
        while (rowEvil >= 0 && colEvil >= 0) {
            if (rowEvil < matrix.length && colEvil < matrix[0].length) {
                matrix[rowEvil][colEvil] = 0;
            }
            rowEvil--;
            colEvil--;
        }
    }

    private static int[] readPosition(String command) {
        return Arrays.stream(command.split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private static void fillField(int rows, int cols, int[][] matrix) {
        int value = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                matrix[row][col] = value++;
            }
        }
    }
}


