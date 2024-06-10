package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SumLine {
    public static void main(String[] args) {
        String path = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\input.txt";

        try {
            BufferedReader bufferedReader = new BufferedReader((new FileReader(path)));

            String line = bufferedReader.readLine();

            while(line != null) {
                int sum = 0;
                for (int i = 0; i < line.length() ; i++) {
                    char symbol = line.charAt(i);
                    sum += symbol;
                }
                System.out.println(sum);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
