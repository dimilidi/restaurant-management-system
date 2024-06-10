package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_LAB;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MathPotato {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        PriorityQueue<String> queue = new PriorityQueue<>();
        String input = scanner.nextLine();
        int n = Integer.parseInt(scanner.nextLine());

        Arrays.stream(input.split("\\s+")).forEach(queue::offer);
        int cycle = 1;
        while (queue.size() > 1) {
            for (int i = 1; i < n; i++) {
                queue.offer(queue.poll());
            }
            if (isPrime(cycle)){
                System.out.println("Prime " + queue.peek());
            }else {
                System.out.println("Removed " + queue.poll());
            }
            cycle ++;
        }
        System.out.println("Last is " + queue.peek());
    }

    private static boolean isPrime(int cycle) {
        if (cycle == 1){
            return false;
        }
        for (int i = 2; i < cycle; i++) {
            if (cycle % i == 0){
                return false;
            }
        }
        return true;
    }
}
