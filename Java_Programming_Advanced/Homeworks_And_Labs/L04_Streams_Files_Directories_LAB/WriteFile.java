package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class WriteFile {
    public static void main(String[] args) throws  IOException{

        Set<Character> punctuations = Set.of(',', '.', '!', '?');
        String dir = System.getProperty("user.dir");
        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";

      /*  FileOutputStream class in Java writes data to a file byte by byte -> write()
        It creates the file if it doesn't exist, and if the file already exists, it will be truncated (cleared) before writing.
     */

        FileInputStream inputStream = new FileInputStream(path);
        FileOutputStream outputStream = new FileOutputStream("write-to-file-output.txt");

        int oneByte = inputStream.read();

        while(oneByte >= 0){
            char symbol = (char) oneByte;
            if(!punctuations.contains(symbol)) {
                outputStream.write(symbol);
            }
            oneByte = inputStream.read();

        }
        inputStream.close();
        outputStream.close();
    }
}
