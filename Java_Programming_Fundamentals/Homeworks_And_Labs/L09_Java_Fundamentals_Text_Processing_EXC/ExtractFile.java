package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class ExtractFile {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] filePathArray = scanner.nextLine().split("\\\\");
        String[] nameAndExtension = filePathArray[filePathArray.length - 1].split("\\.");
        String name = nameAndExtension[0];
        String extension = nameAndExtension[1];

        System.out.printf("File name: %s%nFile extension: %s",name, extension);
    }
}
