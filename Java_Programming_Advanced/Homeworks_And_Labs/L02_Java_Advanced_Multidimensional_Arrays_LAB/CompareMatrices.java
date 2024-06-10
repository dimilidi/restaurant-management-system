package Homeworks_And_Labs.L02_Java_Advanced_Multidimensional_Arrays_LAB;

import java.util.Arrays;
import java.util.Scanner;

public class CompareMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[][] firstArray = readDimArray(scanner);
        int[][] secondArray = readDimArray(scanner);

      printAreEqualArr(firstArray, secondArray);
    }

    private static int[][] readDimArray(Scanner scanner) {
        int[] dimensions = getArray(scanner);

        int firstArrayRows = dimensions[0];
        int firstArrayCols = dimensions[1];

        int[][] firstArray = new int[firstArrayRows][];

        for (int i = 0; i < firstArrayRows; i++) {
            int[] arr =  getArray(scanner);

            firstArray[i] = arr;
        }
        return firstArray;
    }

    private static int[] getArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static boolean areEqual(int[][] arr1, int[][] arr2) {
        if(arr1.length != arr2.length) {
            return false;
        }

        for (int row = 0; row < arr1.length; row++) {
            if(arr1[row].length != arr2[row].length) {
                return false;
            }

            for (int col = 0; col < arr1[row].length ; col++) {
                if(arr1[row][col] != arr2[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void printAreEqualArr(int[][] arr1, int[][] arr2) {
       if( areEqual(arr1, arr2)) {
           System.out.println("equal");
       } else {
           System.out.println("not equal");
       }
    }
}
