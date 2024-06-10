package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.System.out;

public class ExtractIntegers {
    public static void main(String[] args) throws FileNotFoundException {
        String dir = System.getProperty("user.dir");
        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";

        Scanner scanner = new Scanner(new FileInputStream(path));

        // Buffered Stream
        PrintWriter printer = new PrintWriter(new FileOutputStream("extract-integers.txt"));
        while (scanner.hasNext()) {
            if (scanner.hasNextInt())
                printer.println(scanner.nextInt());
            scanner.next();
        }
        // prevent leaks, includes flush, get result in the file
        printer.close();
        // get result in the file immediately and clear the buffer
        printer.flush();
    }
}
