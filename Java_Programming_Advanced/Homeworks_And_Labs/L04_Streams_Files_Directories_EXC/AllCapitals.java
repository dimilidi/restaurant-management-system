package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AllCapitals {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";
        String outputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\allCapitals-output.txt";

        try ( BufferedReader reader = Files.newBufferedReader(Path.of(inputPath));
            BufferedWriter writer = Files.newBufferedWriter(Path.of(outputPath));){

            String line = reader.readLine();
            while(line != null) {
                writer.write(line.toUpperCase());
                // writer.write(System.lineSeparator());
                writer.newLine();
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
