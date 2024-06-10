package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_EXC;

import java.util.Scanner;

public class RecursiveFibonacci {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        long result = fib(n);
        System.out.println(result);
    }

    private static long fib(int n) {
        if(n < 2) {
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
}
