package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class CountCharsInString {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String inputText = scanner.nextLine().trim();
        Map<Character, Integer> charMap = new LinkedHashMap<>();

        for (int i = 0; i < inputText.length(); i++) {
            char letter = inputText.charAt(i);
            if (letter != ' ') {
                charMap.put(letter, charMap.getOrDefault(letter, 0) + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
