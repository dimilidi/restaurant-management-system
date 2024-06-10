package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_EXC;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class ReverseNumbersStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> stack = new ArrayDeque<>();
    /*    String[] input = scanner.nextLine().split("\\s+");

        // Add elements to the stack
        for(String el : input) {
           int number =  Integer.parseInt(el);
           stack.push(number);
        }*/
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);
        //Remove elements from the stack
        while(!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }



    }
}
