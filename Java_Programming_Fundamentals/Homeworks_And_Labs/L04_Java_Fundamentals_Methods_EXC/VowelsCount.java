package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class VowelsCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        System.out.println(countVowels((str)));
    }

    public static int countVowels(String text) {
        String[] vowels = {"a", "e", "i", "o", "u"};
        int countVowels = 0;
        String[] letter = text.split("");

        for (int i = 0; i < letter.length; i++) {
            for (int j = 0; j < vowels.length; j++) {
                 if(vowels[j].equals(letter[i].toLowerCase())) {
                     countVowels++;
                 }
            }
        }
        return countVowels;
    }
}
