package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.*;

public class FoodFinder_JavaAdvancedRegularExam19February2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<String> vowelsQueue = new ArrayDeque<>();
        Deque<String> consonantsStack = new ArrayDeque<>();
        Map<String, Set<String>> wordsMap = new LinkedHashMap<>();
        String[] words = {"pear", "flour", "pork", "olive"};

        fillQueue(scanner, vowelsQueue);
        fillStack(scanner, consonantsStack);
        fillMap(wordsMap, words);


        while(!consonantsStack.isEmpty()) {
            String vowel = vowelsQueue.poll();
            String consonant = consonantsStack.pop();

                wordsMap.entrySet().forEach(entry -> {
                    if(entry.getKey().contains(vowel)) {
                        entry.getValue().add(vowel);
                    }
                    if (entry.getKey().contains(consonant)) {
                        entry.getValue().add(consonant);
                    }
                });

            vowelsQueue.offer(vowel);
        }

        // Output
       List<String> wordsFoundList = new LinkedList<>();

        wordsMap.entrySet().forEach(entry -> {
         if (entry.getKey().length() ==  entry.getValue().size()) {
             wordsFoundList.add(entry.getKey());
                    };
                });
        System.out.println("Words found: " + wordsFoundList.size());
        wordsFoundList.stream().forEach(System.out::println);
    }

    private static void fillMap(Map<String, Set<String>> wordsMap, String[] words) {
        for(String word : words) {
            wordsMap.put(word,  new LinkedHashSet<>());
        }
    }

    private static void fillStack(Scanner scanner, Deque<String> consonantsStack) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(consonantsStack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<String> vowelsQueue) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(vowelsQueue::offer);
    }
}
