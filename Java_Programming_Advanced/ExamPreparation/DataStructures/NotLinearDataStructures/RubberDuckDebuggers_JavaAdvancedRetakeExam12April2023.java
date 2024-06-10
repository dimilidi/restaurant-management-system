package ExamPreparation.DataStructures.NotLinearDataStructures;

import java.util.*;

public class RubberDuckDebuggers_JavaAdvancedRetakeExam12April2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> timeQueue = new ArrayDeque<>();
        Deque<Integer> tasksStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(timeQueue::offer);
        Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).forEach(tasksStack::push);

        Map<String, Integer[]> duckyTypeMap = new HashMap<>();
        duckyTypeMap.put("Darth Vader Ducky", new Integer[]{0, 60});
        duckyTypeMap.put("Thor Ducky", new Integer[]{61, 120});
        duckyTypeMap.put("Big Blue Rubber Ducky", new Integer[]{121, 180});
        duckyTypeMap.put("Small Yellow Rubber Ducky", new Integer[]{181, 240});

        Map<String, Integer> earnedDucksMap = new LinkedHashMap<>();
        earnedDucksMap.put("Darth Vader Ducky", 0);
        earnedDucksMap.put("Thor Ducky", 0);
        earnedDucksMap.put("Big Blue Rubber Ducky", 0);
        earnedDucksMap.put("Small Yellow Rubber Ducky", 0);

        while (!tasksStack.isEmpty() && !timeQueue.isEmpty()) {
            int calculatedTime = timeQueue.peek() * tasksStack.peek();

            if (calculatedTime > 240) {
                // Decrease the number of tasks by 2
                tasksStack.push(tasksStack.pop() - 2);

                // Move the programmer time's value to the end of its sequence
                timeQueue.offerLast(timeQueue.poll());
            } else {
                for (String ducky : duckyTypeMap.keySet()) {
                    Integer[] timeRange = duckyTypeMap.get(ducky);
                    if (calculatedTime >= timeRange[0] && calculatedTime <= timeRange[1]) {
                        earnedDucksMap.put(ducky, earnedDucksMap.get(ducky) + 1);

                        timeQueue.poll();
                        tasksStack.pop();
                        break;  // Break the loop once a duck is earned
                    }
                }
            }
        }

        System.out.println("Congratulations, all tasks have been completed! Rubber ducks rewarded:");
        for(Map.Entry<String, Integer> duckEntry : earnedDucksMap.entrySet()) {
            System.out.println(duckEntry.getKey() + ": " + duckEntry.getValue());
        }
    }
}
