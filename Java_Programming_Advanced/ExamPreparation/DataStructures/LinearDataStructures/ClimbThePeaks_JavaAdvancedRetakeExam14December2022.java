package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.*;

public class ClimbThePeaks_JavaAdvancedRetakeExam14December2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> portionStack = new ArrayDeque<>();
        Deque<Integer> staminaQueue = new ArrayDeque<>();
        Map<String, Integer> mountainMap = new LinkedHashMap<>();
        List<String> conqueredPeaks = new LinkedList<>();

        mountainMap.put("Vihren", 80);
        mountainMap.put("Kutelo", 90);
        mountainMap.put("Banski Suhodol", 100);
        mountainMap.put("Polezhan", 60);
        mountainMap.put("Kamenitza", 70);

        fillStack(scanner, portionStack);
        fillQueue(scanner, staminaQueue);

        List<String> peakNames = new ArrayList<>(mountainMap.keySet());
        for (String peak : peakNames) {
            if (!portionStack.isEmpty() && !staminaQueue.isEmpty()) {
                int currentPortion = portionStack.pop();
                int currentStamina = staminaQueue.poll();
                int sum = currentPortion + currentStamina;
                int difficulty = mountainMap.get(peak);
                if (sum >= difficulty) {
                    conqueredPeaks.add(peak);
                    mountainMap.remove(peak);
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        if (mountainMap.isEmpty()) {
            System.out.println("Alex did it! He climbed all top five Pirin peaks in one week -> @FIVEinAWEEK");
        } else {
            System.out.println("Alex failed! He has to organize his journey better next time -> @PIRINWINS");
        }

        if (!conqueredPeaks.isEmpty()) {
            System.out.println("Conquered peaks:");
            conqueredPeaks.forEach(System.out::println);
        }
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
