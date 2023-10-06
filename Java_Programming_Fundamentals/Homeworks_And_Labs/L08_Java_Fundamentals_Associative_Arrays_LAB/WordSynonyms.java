package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_LAB;

import java.util.*;

public class WordSynonyms {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countLines = Integer.parseInt(scanner.nextLine());
        Map<String, ArrayList<String>> dictionary = new LinkedHashMap<>();

        for (int i = 0; i < countLines; i++) {
            String word = scanner.nextLine();
            String synonym = scanner.nextLine();
            if(!dictionary.containsKey(word)) {
                dictionary.put(word, new ArrayList<>());
            }
            dictionary.get(word).add(synonym);
        }

        for (Map.Entry<String, ArrayList<String>> entry : dictionary.entrySet()) {
            String key = entry.getKey();
            String value = String.join(", ", entry.getValue());
            System.out.printf("%s - %s\n", key, value);
        }
    }
}
