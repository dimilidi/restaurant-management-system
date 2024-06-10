package Homeworks_And_Labs.L04_Streams_Files_Directories_EXC;

import java.io.File;

public class GetFolderSize {
    public static void main(String[] args) {
        String pathFolder = "C:\\Users\\dimit\\Documents\\SOFTUNI\\SofUni_Software_Engineering\\Java_Programming_Advanced\\Homeworks_And_Labs\\Streams_Exc_Resources\\04. Java-Advanced-Files-and-Streams-Exercises-Resources\\Exercises Resources";
        File folder = new File(pathFolder);

        File[] allFilesInFolder = folder.listFiles();

        int folderSize = 0;
        if (allFilesInFolder != null) {
            for (File file : allFilesInFolder) {
                folderSize += file.length();
            }
        }

        System.out.println("Folder size: " + folderSize);
    }
}
