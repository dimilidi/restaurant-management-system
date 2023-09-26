package Homeworks_And_Labs.L02_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class ZigZagArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countLines = Integer.parseInt(scanner.nextLine());
        String resultEvenLine = "";
        String resultOddLine = "";

        for (int i = 0; i < countLines; i++) {
            String[] arr = scanner.nextLine().split(" ");

            if(i % 2 == 0) {
                resultEvenLine += arr[0] + " ";
                resultOddLine += arr[1] + " ";
            } else {
                resultEvenLine += arr[1] + " ";
                resultOddLine += arr[0] + " ";
            }
        }

        System.out.println(resultEvenLine);
        System.out.println(resultOddLine);

    }
}
