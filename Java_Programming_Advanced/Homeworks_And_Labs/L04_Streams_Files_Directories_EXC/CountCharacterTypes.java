package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

public class CountCharacterTypes {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";
        String outputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\count-characters-type-output.txt";

        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u' );
        Set<Character> punctuation = Set.of('!', ',', '.', '?' );
        int vowelsCounter = 0;
        int punctuationCounter = 0;
        int othersCounter = 0;

        try (BufferedReader reader = Files.newBufferedReader(Path.of(inputPath));
             BufferedWriter writer = Files.newBufferedWriter(Path.of(outputPath));){

            String line = reader.readLine();
            while(line != null) {
                for (char c : line.toCharArray()) {
                    if(vowels.contains(c)) {
                        vowelsCounter++;
                    } else if (punctuation.contains(c)) {
                        punctuationCounter++;
                    } else if (c != ' ') {
                        othersCounter++;
                    }
                }
                line = reader.readLine();
            }

            writer.write(String.format("Vowels: %d%n", vowelsCounter));
            writer.write(String.format("Other symbols: %d%n", othersCounter));
            writer.write(String.format("Punctuation: %d", punctuationCounter));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
