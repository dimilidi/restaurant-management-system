package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_EXC;

import java.util.*;

public class BasicQueueOperations {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Deque<Integer> queue = new ArrayDeque<>();

        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        int n = tokens[0];
        int s = tokens[1];
        int x = tokens[2];

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .forEach(queue::offer);

        // Remove s elements from the queue
        for (int i = 0; i < s; i++) {
            queue.poll();
        }

        if(queue.isEmpty()) {//check for empty queue
            System.out.println(0);
        } else if(queue.contains(x)) { //check if x is present in the queue
            System.out.println(true);
        } else {// else get the smallest number
            Integer smallestElement = Collections.min(queue);
            System.out.println(smallestElement);
        }
    }
}
