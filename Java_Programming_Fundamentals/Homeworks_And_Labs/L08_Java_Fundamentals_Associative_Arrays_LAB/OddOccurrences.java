package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_LAB;

import java.util.*;

public class OddOccurrences {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] wordSequenceArr = scanner.nextLine().split(" ");
        Map<String, Integer> wordMap = new LinkedHashMap<>();

        for (String word : wordSequenceArr) {
            word = word.toLowerCase();
            wordMap.putIfAbsent(word, 0);
            wordMap.put(word, wordMap.get(word) + 1);
//            if (!wordMap.containsKey(word)) {
//                wordMap.put(word, 1);
//            } else {
//                wordMap.put(word, wordMap.get(word) + 1);
//            }
        }

        List<String> oddList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            if(entry.getValue() % 2 != 0) {
                if(!oddList.contains(word)){
                    oddList.add(word);
                }
            }
        }

        System.out.println(String.join(", ", oddList));
    }
}
