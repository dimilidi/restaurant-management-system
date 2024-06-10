package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_EXC;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class RecursiveFibonacciStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        long result = fib(n);
        System.out.println(result);
    }

    private static long fib(int n){
        Deque<Long> stack = new ArrayDeque<>();

        stack.push(0L);
        stack.push(1L);
        if (n < 2) {
            return 1;
        }

        for (int i = 0; i < n; i++) {
            long num1 = stack.pop();
            long num2 = stack.pop();

            // rewrite num1
            stack.push(num1);
            // calculate num2
            stack.push(num1 + num2);
        }
        return stack.pop();
    }
}
