package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

import static java.lang.Math.max;

public class WormsAndHoles {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> wormsStack = new ArrayDeque<>();
        Deque<Integer> holesQueue = new ArrayDeque<>();

         Arrays.stream(scanner.nextLine().split("\\s+"))
                 .map(Integer::parseInt)
                 .forEach(wormsStack::push);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(holesQueue::offer);


        int n = max(wormsStack.size(), holesQueue.size());
        int matchesCount = 0;
        int initialWormsCount = wormsStack.size();


        for (int i = 0; i < n; i++) {
            if(wormsStack.size() > 0 && holesQueue.size() > 0) {
                // If their values are equal, the worm fits the hole and can get into it.
                // After that, you should remove both of them from their sequences.
                if(wormsStack.peek().equals(holesQueue.peek())) {
                    matchesCount++;
                    wormsStack.pop();
                    holesQueue.poll();
                // Otherwise, you should remove the hole and decrease the value of the worm by 3
                } else {
                    holesQueue.poll();
                    int decreasedWormValue = wormsStack.pop() - 3;
                    //If the worm value becomes equal to or below 0,
                    // remove it from the sequence before trying to match it with the hole.
                    if(decreasedWormValue > 0) {
                        wormsStack.push(decreasedWormValue);
                    }
                }
            }
        }

        if (matchesCount > 0) {
            System.out.printf("Matches: %d%n", matchesCount);
        } else {
            System.out.println("There are no matches.");
        }


        if(matchesCount == initialWormsCount && wormsStack.size() == 0) {
            System.out.println("Every worm found a suitable hole!");
        } else if(matchesCount < initialWormsCount && wormsStack.size() == 0) {
            System.out.println("Worms left: none");
        } else if(wormsStack.size() > 0 ){
            ArrayDeque<Integer> leftWorms = new ArrayDeque<>();
            while (!wormsStack.isEmpty()) {
                leftWorms.push(wormsStack.pop());
            }

            String result = leftWorms.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));

            System.out.printf("Worms left: %s%n", result);
        }


        if(holesQueue.size() == 0) {
            System.out.println("Holes left: none");
        } else {
            String leftHoles = holesQueue.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));
            System.out.printf("Holes left: %s%n", leftHoles);
        }
    }
}
