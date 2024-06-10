package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.*;

public class ApocalypsePreparation_JavaAdvancedRegularExam18February2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> textileQueue = new ArrayDeque<>();
        Deque<Integer> medsStack = new ArrayDeque<>();
        Map<String, Integer> healingItemsMap = new LinkedHashMap<>();
        Map<String, Integer> createdItemsMap = new TreeMap<>();

        fillQueue(scanner, textileQueue);
        fillStack(scanner, medsStack);

        healingItemsMap.put("Patch", 30);
        healingItemsMap.put("Bandage", 40);
        healingItemsMap.put("MedKit", 100);

        while (!textileQueue.isEmpty() && !medsStack.isEmpty()) {
            Integer currentTextile = textileQueue.poll();
            Integer currentMeds = medsStack.pop();

            int sum = currentMeds + currentTextile;
            boolean matchFound = false;

            for (Map.Entry<String, Integer> healingItemEntry : healingItemsMap.entrySet()) {
                String healingItem = healingItemEntry.getKey();
                Integer resources = healingItemEntry.getValue();

                if (sum == resources) {
                    createItem(createdItemsMap, healingItem);
                    matchFound = true;
                    break;
                }
            }

            if (!matchFound) {
                if (sum > 100) {
                    createItem(createdItemsMap, "MedKit");
                    int remainingResources = sum - 100;

                    if (!medsStack.isEmpty()) {
                        int nextMeds = medsStack.pop();
                        nextMeds += remainingResources;
                        medsStack.push(nextMeds);
                    }

                } else {
                    currentMeds += 10;
                    medsStack.push(currentMeds);
                }
            }
        }


        if (textileQueue.isEmpty() && medsStack.isEmpty()) {
            System.out.println( "Textiles and medicaments are both empty.");
        } else if (medsStack.isEmpty()) {
            System.out.println("Medicaments are empty.");
        } else if(textileQueue.isEmpty()) {
        System.out.println("Textiles are empty.");
         }

        if (!createdItemsMap.isEmpty()) {
            createdItemsMap.entrySet().stream()
                    .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .forEach(e -> System.out.printf("%s - %d%n", e.getKey(), e.getValue()));
        }


        if(!medsStack.isEmpty()) {
            System.out.print("Medicaments left: ");
            System.out.println(medsStack.toString().substring(1, medsStack.toString().length() - 1));
        } else if(!textileQueue.isEmpty()) {
            System.out.print("Textiles left: ");
            System.out.println(textileQueue.toString().substring(1, textileQueue.toString().length() - 1));
        }


    }

    private static void createItem(Map<String, Integer> createdItemsMap, String healingItem) {
        if(!createdItemsMap.containsKey(healingItem)) {
            createdItemsMap.put(healingItem, 0);
        }
        createdItemsMap.put(healingItem, createdItemsMap.get(healingItem) + 1);
    }

    private static void fillStack(Scanner scanner, Deque<Integer> stack) {
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(stack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<Integer> queue) {
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .forEach(queue::offer);
    }
}
