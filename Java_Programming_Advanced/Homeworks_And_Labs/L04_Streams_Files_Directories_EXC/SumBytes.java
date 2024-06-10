package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SumBytes {
    public static void main(String[] args) {
        String path = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";
        int sum = 0;
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(path));

            String line = bufferedReader.readLine();

            
            while(line != null) {
                for (char c :  line.toCharArray()) {
                    sum += c;
                }
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sum);

    }
}
