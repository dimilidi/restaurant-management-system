package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class CharactersInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String letterOne = scanner.nextLine();
        String letterTwo = scanner.nextLine();
        char characterOne = letterOne.charAt(0);
        char characterTwo = letterTwo.charAt(0);

        printCharactersInRange(characterOne, characterTwo);
    }

    public static void printCharactersInRange(char charOne, char charTwo) {
        if (charOne > charTwo) {
            char temp = charOne;
            charOne = charTwo;
            charTwo = temp;
        }
        for (int i = charOne + 1; i < charTwo; i++) {
            System.out.printf("%c ", (char) i);
        }
    }
}

