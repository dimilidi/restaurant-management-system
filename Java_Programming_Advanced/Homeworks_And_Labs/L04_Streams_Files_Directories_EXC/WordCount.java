package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws IOException {
        String inputPathWords = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\words.txt";
        String inputPathText = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\text.txt";
        String outputPathResult = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\word-count-result.txt";

        List<String> allLinesWithWords = Files.readAllLines(Path.of(inputPathWords));

        //word -> count
        Map<String, Integer> countWords = new HashMap<>();

        for (String line : allLinesWithWords){
            //line = "of which The".split(" ") -> ["of", "which", "The"]
            String [] wordsOnCurrentRow = line.split("\\s+");
            Arrays.stream(wordsOnCurrentRow).forEach(word -> {
                countWords.put(word, 0);
            });
        }

        // countWords -> кои са думите, които търсим
        List<String> allLinesWithText = Files.readAllLines(Path.of(inputPathText));
        for (String line : allLinesWithText) {
            //line = "There are many variations of passages of Lorem Ipsum available".split(" ")
            //remove punctuation -> , ?, !, :
            line = line.replaceAll("[\\.\\,\\?\\!\\:]", "");
            String [] words = line.split("\\s+"); // words on current line
            for (String word : words) {
                if (countWords.containsKey(word)) {
                    countWords.put(word, countWords.get(word) + 1);
                }
            }
        }

        PrintWriter writer = new PrintWriter(outputPathResult);
        countWords.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) //sorted map
                .forEach(entry -> writer.println(entry.getKey() + " - " + entry.getValue()));
        writer.close();

        //sorted (число)
        //число е 0 -> не разменя местата на записите
        //число е 1 -> разменя местата на записите
        //число е - 1 -> не разменя местата на записите

        //compareTo -> връща цяло число
        //0 -> първото == второто
        //1 -> първото > второто
        //-1 -> второто > първото

    }
}