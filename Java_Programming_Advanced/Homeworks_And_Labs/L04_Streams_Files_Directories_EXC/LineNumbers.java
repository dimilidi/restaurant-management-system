package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.*;

public class LineNumbers {
    public static void main(String[] args) {
        String inputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt";
        String outputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\line-numbers-output.txt";


        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {

            int lineNumber = 1;
            String line;

            while ((line = reader.readLine()) != null) {
                String lineWithNumber = lineNumber + ". " + line;
                writer.write(lineWithNumber);
                writer.newLine();
                lineNumber++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
