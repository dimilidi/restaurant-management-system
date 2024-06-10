package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class CreateZipArchive {
    public static void main(String[] args) {
        String inputPath1 = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputTwo.txt";
        String inputPath2 = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputOne.txt";
        String inputPath3 = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\inputLineNumbers.txt";
        String outputPath = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\zip-output.zip";

        List<String> paths = List.of(inputPath1, inputPath2, inputPath3);

        try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)
        ) {

            for (String path : paths) {
                File file = new File(path);
                FileInputStream fileInputStream = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getName());

                zipOutputStream.putNextEntry(zipEntry);

                int oneByte = fileInputStream.read();

                // fill files in the zip with data
                while (oneByte != -1) {
                    zipOutputStream.write(oneByte);

                    oneByte = fileInputStream.read();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
