package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CardsGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> firstHandOfCards = Arrays
            .stream(scanner.nextLine()
            .split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        List<Integer> secondHandOfCards = Arrays
            .stream(scanner.nextLine()
            .split(" "))
            .map(Integer::parseInt)
            .collect(Collectors.toList());
        String winner = "";

        while (!firstHandOfCards.isEmpty() && !secondHandOfCards.isEmpty()) {
            int firstPlayerCard = firstHandOfCards.get(0);
            int secondPlayerCard = secondHandOfCards.get(0);

            if (firstPlayerCard == secondPlayerCard) {
                firstHandOfCards.remove(0);
                secondHandOfCards.remove(0);
            } else if (firstPlayerCard > secondPlayerCard) {
                firstHandOfCards.add(firstPlayerCard);
                firstHandOfCards.add(secondPlayerCard);
                firstHandOfCards.remove(0);
                secondHandOfCards.remove(0);
            } else {
                secondHandOfCards.add(secondPlayerCard);
                secondHandOfCards.add(firstPlayerCard);
                firstHandOfCards.remove(0);
                secondHandOfCards.remove(0);
            }
        }

        if (firstHandOfCards.isEmpty()) {
            int sum = secondHandOfCards.stream().mapToInt(Integer::intValue).sum();
            System.out.printf("Second player wins! Sum: %d%n", sum);
        } else {
            int sum = firstHandOfCards.stream().mapToInt(Integer::intValue).sum();
            System.out.printf("First player wins! Sum: %d%n", sum);
        }

    }
}
