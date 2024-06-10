package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileRederAndWriter {
    public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.dir");
        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";
        // bigger charset from UTF encoding, not only ASCII and extended ASCII
        //  steps on the FileInputStream
        // read / write character by character
        FileReader reader = new FileReader(path);
        reader.read();
    }
}
