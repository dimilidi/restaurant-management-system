package FinalExam;

import java.util.*;

public class Problem_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read pairs of words and their definitions
        Map<String, List<String>> vocabulary = new LinkedHashMap<>();
        String[] inputPairs = scanner.nextLine().split(" \\| ");
        for (String pair : inputPairs) {
            String[] wordAndDefinition = pair.split(": ");
            String word = wordAndDefinition[0];
            String definition = wordAndDefinition[1];

            vocabulary.putIfAbsent(word, new ArrayList<>());
            vocabulary.get(word).add(definition);
        }

        // Read words to test
        String[] wordsToTest = scanner.nextLine().split(" \\| ");

        // Read the command
        String command = scanner.nextLine();

        // Process the command
        if (command.equals("Test")) {
            testWords(vocabulary, wordsToTest);
        } else if (command.equals("Hand Over")) {
            handOverWords(vocabulary);
        }

        scanner.close();
    }

    private static void testWords(Map<String, List<String>> vocabulary, String[] wordsToTest) {
        for (String word : wordsToTest) {
            if (vocabulary.containsKey(word)) {
                System.out.println(word + ":");
                for (String definition : vocabulary.get(word)) {
                    System.out.println(" -" + definition);
                }
            }
        }
    }

    private static void handOverWords(Map<String, List<String>> vocabulary) {
        System.out.println(String.join(" ", vocabulary.keySet()));
    }
}
