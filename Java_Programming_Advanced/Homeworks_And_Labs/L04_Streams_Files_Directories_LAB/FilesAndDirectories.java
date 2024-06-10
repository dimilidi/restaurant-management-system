package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FilesAndDirectories {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String dir = System.getProperty("user.dir");

        File file = new File(dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/my-file.txt");
        file.createNewFile();
        file.delete();

    }
}
