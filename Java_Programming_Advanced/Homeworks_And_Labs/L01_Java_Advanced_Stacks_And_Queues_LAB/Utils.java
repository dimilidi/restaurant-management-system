package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_LAB;

import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class Utils {
    public static void main(String[] args) {

    }

    private static void fillStack(Scanner scanner, Deque<Integer> stack) {
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<Integer> queue) {
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(queue::offer);
    }
}
