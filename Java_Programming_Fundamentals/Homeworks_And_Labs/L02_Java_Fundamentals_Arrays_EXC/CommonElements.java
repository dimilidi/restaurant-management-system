package Homeworks_And_Labs.L02_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class CommonElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str1 = scanner.nextLine();
        String str2 = scanner.nextLine();
        String[] arr1 = str1.split(" ");
        String[] arr2 = str2.split(" ");

        for (int i = 0; i < arr2.length; i++) {
            for (int j = 0; j < arr1.length; j++) {
                if(arr1[j].equals(arr2[i])){
                    System.out.print(arr2[i] + " ");;
                }


            }
        }
    }
}
