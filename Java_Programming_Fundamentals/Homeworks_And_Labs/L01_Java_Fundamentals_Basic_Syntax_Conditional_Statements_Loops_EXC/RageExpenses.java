package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class RageExpenses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lostGamesCount = Integer.parseInt(scanner.nextLine());
        double headsetPrice = Double.parseDouble(scanner.nextLine());
        double mousePrice = Double.parseDouble(scanner.nextLine());
        double keyboardPrice = Double.parseDouble(scanner.nextLine());
        double displayPrice = Double.parseDouble(scanner.nextLine());

        int trashedHeadsetCount = lostGamesCount / 2;
        int trashedMouseCount = lostGamesCount / 3;
        int trashedKeyboardCount = lostGamesCount / 6;
        int trashedDesktop = trashedKeyboardCount / 2;

        double totalExpenses = (trashedHeadsetCount * headsetPrice)
                                + (trashedMouseCount * mousePrice)
                                + (trashedKeyboardCount * keyboardPrice)
                                + (trashedDesktop * displayPrice);

        System.out.printf("Rage expenses: %.2f lv.", totalExpenses);

    }
}
