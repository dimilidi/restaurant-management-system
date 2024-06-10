package Homeworks_And_Labs.L04_Streams_Files_Directories_LAB;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        // Dynamic path
        String dir = System.getProperty("user.dir");

        String path = dir + "/Java_Programming_Advanced/Homeworks_And_Labs/Streams/04. Java-Advanced-Files-and-Streams-Lab-Resources/input.txt";

        /*FileInputStream class reads data from a file byte by byte. The read() method of FileInputStream
        reads a byte of data from the file and returns it as an integer value. If the end of the file is reached, it returns -1.*/

        // try with resources -> closes the file
        try (FileInputStream inputStream = new FileInputStream(path)){

            int oneByte = inputStream.read();
            while(oneByte >= 0){
               // System.out.print((char) oneByte);
                System.out.printf("%s ", Integer.toBinaryString(oneByte));
                oneByte = inputStream.read();
            }
            System.out.println(oneByte);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
