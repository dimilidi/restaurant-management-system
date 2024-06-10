package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WriteEveryThirdLine {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        String dir = System.getProperty("user.dir");
        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";

        // BufferedReader and Scanner read line by line

        BufferedReader reader = new BufferedReader(new FileReader(path));
       // PrintWriter writer = new PrintWriter(new FileWriter(path));
        int counter = 1;
        String line = reader.readLine();
        List<String> lines = new ArrayList<>();

        while (line != null) {
            if (counter % 3 == 0) {
                lines.add(line);
               // System.out.println(line);
              //  writer.println(line);
            }
            counter++;
            line = reader.readLine();
             }
        Path pathObj = Paths.get("write-every-third-line.txt");
        Files.write(pathObj, lines);

    }
}
