package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class NxNMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());

        printMatrix(num);
    }

    public static void printMatrix(int num){
        for (int i = 0; i < num; i++) {
            System.out.println();
            for (int j = 0; j < num ; j++) {
                System.out.print(num + " ");
            }
        }
    }
}
