package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class CharacterMultiplier {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] strings = scanner.nextLine().split(" ");
        String str1 = strings[0];
        String str2 = strings[1];
        int sum = 0;
        int difference = 0;
        String longerStr = "";

        int length = Math.min(str1.length(), str2.length());

        if(str1.length() > str2.length()) {
            difference = str1.length() - length;
            longerStr = str1;
        } else {
            difference = str2.length() - length;
            longerStr = str2;
        }


        for (int i = 0; i < length; i++) {
            int product = str1.charAt(i) * str2.charAt(i);
            sum += product;
        }

        if(difference > 0){
            for (int i = longerStr.length() - 1; i >= longerStr.length() - difference; i--) {
                char charToAdd = longerStr.charAt(i);
                sum += charToAdd;
            }
        }

        System.out.println(sum);
    }
}
