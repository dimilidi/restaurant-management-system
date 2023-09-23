package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class NumbersN1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            System.out.println(n-i);
        }
    }
}
