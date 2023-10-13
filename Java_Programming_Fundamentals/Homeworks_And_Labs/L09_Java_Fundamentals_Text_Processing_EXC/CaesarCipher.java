package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String encrypted = "";
        String text = scanner.nextLine();

        for (int i = 0; i < text.length(); i++) {
            char c = (char) (text.charAt(i) + 3);
            encrypted += c;
        }
        System.out.println(encrypted);
    }
}
