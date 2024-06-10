package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SortLines {
    public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.dir");

        Path path = Paths.get(dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt");
        List<String> lines = Files.readAllLines(path)
                .stream()
                .sorted()
                .collect(Collectors.toList());
        Path outputPath = Paths.get("sort-lines-output.txt");
        Files.write(outputPath, lines);
    }
}
