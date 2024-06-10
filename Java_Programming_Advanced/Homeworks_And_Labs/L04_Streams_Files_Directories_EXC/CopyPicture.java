package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyPicture {
    public static void main(String[] args) {

        String inputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\picture.jpg";
        String outputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\picture-copy.jpg";

        try (FileInputStream fileInputStream = new FileInputStream(inputPath);
             FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {

            int oneByte = fileInputStream.read();

            while (oneByte != -1) {

                fileOutputStream.write(oneByte);

                oneByte = fileInputStream.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
