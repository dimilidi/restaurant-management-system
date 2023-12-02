package Final_Exam_Preparation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiDetector {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        int threshold = findThreshold(input);
        List<String> emojis = extractEmojis(input);
        int emojisCount = emojis.size();

        System.out.printf("Cool threshold: %s%n%d emojis found in the text. The cool ones are: %n", threshold, emojisCount);
        for (String emoji : emojis) {
            if(isCool(emoji, threshold)) {
                System.out.println(emoji);
            }
        }
    }

    private static List<String> extractEmojis(String input) {
        Pattern pattern = Pattern.compile("(::|\\*\\*)(?<emoji>[A-Z][a-z]{2,})\\1");
        Matcher matcher = pattern.matcher(input);
        List<String> emojis = new ArrayList<>();

        while (matcher.find()) {
           String emoji = matcher.group();
           emojis.add(emoji);
        }
        return emojis;
    }

    private static boolean isCool(String word, int threshold) {
        int coolness = 0;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);
            if(Character.isLetter(letter)) {
                coolness += letter;
            }
        }

        return coolness >= threshold;
    }

    private static int findThreshold(String input) {
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(input);

        int threshold = 1;
       while(matcher.find()){
           String digit = matcher.group();
           threshold *= Integer.parseInt(digit);
       }
        return threshold;
    }
}

