package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_LAB;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<String> stack = new ArrayDeque<>();
        ArrayDeque<String> stackReversed = new ArrayDeque<>();
        String exp = scanner.nextLine();
        Arrays.stream(exp.split("\\s+")).forEach(stack::push);

        while (!stack.isEmpty()){
            stackReversed.push(stack.pop());
        }

        while (stackReversed.size() > 1) {
            int firstNum = Integer.parseInt(stackReversed.pop());
            String operation = stackReversed.pop();
            int secondNum = Integer.parseInt(stackReversed.pop());

            if ("+".equalsIgnoreCase(operation)){
                stackReversed.push(firstNum + secondNum + "");
            }else {
                stackReversed.push(firstNum - secondNum + "");

            }
        }
        System.out.println(stackReversed.peek());
    }
}
