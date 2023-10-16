package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class StringExplosion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StringBuilder sb = new StringBuilder(scanner.nextLine());

        int totalStrength = 0;
        for (int i = 0; i < sb.length(); i++) {
            char character = sb.charAt(i);

            if (character == '>') {
                int strength = Integer.parseInt(String.valueOf(sb.charAt(i + 1)));
                totalStrength += strength;
            } else if (character != '>' && totalStrength > 0) {
                sb.deleteCharAt(i);
                totalStrength--;
                i--;
            }
        }

        System.out.println(sb);

    }
}
