package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class ArrayRotation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String stringInput = scanner.nextLine();
        String[] arr = stringInput.split(" ");
        int rotations = Integer.parseInt(scanner.nextLine());
        String result = "";

        if(rotations > arr.length) {
            rotations = rotations % arr.length; // effective number of rotations
        }

        String endElements = "";
        for (int i = 0; i < rotations; i++) {
            endElements += arr[i] + " ";
        }

        String restElements = "";
        for (int i = rotations; i < arr.length; i++) {
            restElements += arr[i] + " ";
        }

        result = restElements +  endElements;

        System.out.println(result);
    }
}
