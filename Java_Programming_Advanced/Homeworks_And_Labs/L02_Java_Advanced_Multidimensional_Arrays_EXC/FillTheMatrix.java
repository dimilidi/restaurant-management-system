package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_EXC;

import java.util.Arrays;
import java.util.Scanner;

public class FillTheMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] params = scanner.nextLine().split(",");
        int dimension = Integer.parseInt(params[0]);
        String pattern = params[1];

        int[][] matrix = new int[dimension][dimension];

        if(pattern.equals(" B")) {
            patternB(matrix);
        } else if(pattern.equals(" A")){
            patternA(matrix);
        }

    }

    public static void patternB(int[][] array){
        int dimension = array.length;
        int num = 1;
        for (int col = 0; col < dimension; col++) {
            if(col % 2 == 0) {
                for (int row = 0; row < dimension; row++) {
                    array[row][col] = num++;
                }
            } else {
                for (int row = dimension - 1; row >= 0; row--) {
                    array[row][col] = num++;
                }
            }
        }
        printMultiDimArray(array);
    }

    public static void patternA(int[][] array){
        int dimension = array.length;
        int num = 1;
        for (int col = 0; col < dimension; col++) {
            for (int row = 0; row < dimension; row++) {
                array[row][col] = num++;
            }
        }
        printMultiDimArray(array);
    }
    public static void printMultiDimArray(int[][] array) {

        for (int row = 0; row < array.length; row++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int col= 0; col < array[row].length; col++) {
                stringBuilder.append(array[row][col]).append(" ");

            }
            System.out.println(stringBuilder.toString().trim());
        }
    }

    public static void printMultiDimArrayV2(int[][] array) {

        for (int row = 0; row < array.length; row++) {
            System.out.println(Arrays.toString(array[row]).replaceAll("[\\[\\],]", " "));
        }
    }
}
