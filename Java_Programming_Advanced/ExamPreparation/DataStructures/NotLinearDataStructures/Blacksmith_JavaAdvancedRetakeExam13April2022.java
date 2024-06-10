package ExamPreparation.DataStructures.NotLinearDataStructures;

import java.util.*;

public class Blacksmith_JavaAdvancedRetakeExam13April2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> steelQueue = new ArrayDeque<>();
        Deque<Integer> carbonStack = new ArrayDeque<>();
        Map<String, Integer> swordMap = new LinkedHashMap<>();

        swordMap.put("Gladius", 70);
        swordMap.put("Shamshir", 80);
        swordMap.put("Katana", 90);
        swordMap.put("Sabre", 110);

        fillQueue(scanner, steelQueue);
        fillStack(scanner, carbonStack);

        Map<String, Integer> forgedSwords = new TreeMap<>();

        while (!steelQueue.isEmpty() && !carbonStack.isEmpty()) {
            Integer currentSteel = steelQueue.poll();
            Integer currentCarbon = carbonStack.pop();

            Integer sum = currentSteel + currentCarbon;

            // Create a list of values from the swordMap
            List<Integer> resourcesList = new ArrayList<>(swordMap.values());

            if(!resourcesList.contains(sum)) {
                currentCarbon += 5;
                carbonStack.push(currentCarbon);
            } else {
                // Iterate through the swordMap to find a match with the current sum
                for (Map.Entry<String, Integer> swordEntry : swordMap.entrySet()) {
                    String sword = swordEntry.getKey();
                    Integer resources = swordEntry.getValue();

                    if (sum.equals(resources)) {
                        if(!forgedSwords.containsKey(sword)) {
                            forgedSwords.put(sword, 0);
                        }
                        forgedSwords.put(sword, forgedSwords.get(sword) + 1);
                        break;
                    }
                }
            }
        }

        int totalSwords = forgedSwords.values().stream().mapToInt(Integer::intValue).sum();

        // Output the results
        if (!forgedSwords.isEmpty()) {
            System.out.printf("You have forged %d swords.\n", totalSwords);
        } else {
            System.out.println("You did not have enough resources to forge a sword.");
        }

        System.out.printf("Steel left: %s\n", steelQueue.isEmpty()
                ? "none"
                : steelQueue.toString().substring(1, steelQueue.toString().length() - 1));
        System.out.printf("Carbon left: %s\n", carbonStack.isEmpty()
                ? "none"
                : carbonStack.toString().substring(1, carbonStack.toString().length() - 1));

        forgedSwords.forEach((sword, amount) -> System.out.printf("%s: %d\n", sword, amount));
    }

    private static void fillStack(Scanner scanner, Deque<Integer> stack) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<Integer> queue) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(queue::offer);
    }
}
