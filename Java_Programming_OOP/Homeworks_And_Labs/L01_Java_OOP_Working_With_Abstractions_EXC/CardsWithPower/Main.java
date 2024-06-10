package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.CardsWithPower;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String rank = scanner.nextLine();
        String suit = scanner.nextLine();

        Card card = new Card(rank, suit);
        System.out.println(card);
    }
}
