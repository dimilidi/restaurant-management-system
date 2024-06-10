package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Meeting_JavaAdvancedRetakeExam15December2021 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> maleStack = new ArrayDeque<>();
        Deque<Integer> femaleQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(maleStack::push);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(femaleQueue::offer);

        int matches = 0;

        while(!maleStack.isEmpty() && !femaleQueue.isEmpty()) {
            Integer currentMale = maleStack.peek();
            Integer currentFemale = femaleQueue.peek();
            if(currentFemale == null || currentMale == null) {
                break;
            }

            if( currentMale <= 0) {
                maleStack.pop();
            } else if (currentFemale<= 0) {
                femaleQueue.poll();
            } else if (currentMale % 25 == 0){
                maleStack.pop();
                maleStack.pop();
            } else if (currentFemale % 25 == 0){
                femaleQueue.poll();
                femaleQueue.poll();
            } else if (currentMale.equals(currentFemale)) {
                matches++;
                maleStack.pop();
                femaleQueue.poll();
            } else {
                maleStack.push(maleStack.pop() - 2);
                femaleQueue.poll();
            }
        }


        System.out.println("Matches: " + matches);
        if(maleStack.isEmpty()) {
            System.out.println("Males left: none");
        } else {
            System.out.print("Males left: ");
            String leftMales = maleStack.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(leftMales);
        }

        if(femaleQueue.isEmpty()) {
            System.out.println("Females left: none");
        } else {
            System.out.print("Females left: ");
            String leftFemales = femaleQueue.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            System.out.println(leftFemales);
        }

    }
}
