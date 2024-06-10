package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.*;

public class HandsOfCards {
    private static Map<String, Integer> types = Map.of("S", 4,
                                                       "H", 3,
                                                       "D", 2,
                                                       "C", 1);
    private static Map<String, Integer> cardsPowers = new HashMap<>();

    //  private static LinkedList<String> cardsPowers = Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A");

    // S -> 4,
    // H-> 3,
    // D -> 2,
    // C -> 1
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        cardsPowers.put("2", 2);
        cardsPowers.put("3", 3);
        cardsPowers.put("4", 4);
        cardsPowers.put("5", 5);
        cardsPowers.put("6", 6);
        cardsPowers.put("7", 7);
        cardsPowers.put("8", 8);
        cardsPowers.put("9", 9);
        cardsPowers.put("10", 10);
        cardsPowers.put("J", 11);
        cardsPowers.put("Q", 12);
        cardsPowers.put("K", 13);
        cardsPowers.put("A", 14);


        String input = scanner.nextLine();
        Map<String, HashSet<String>> players = new LinkedHashMap<>();
        while (!input.equals("JOKER")) {
            String[] tokens = input.split(": ");
            String name = tokens[0];

            players.putIfAbsent(name, new HashSet<>());

            Arrays.stream(tokens[1].split(", "))
                    .forEach(players.get(name)::add);

            input = scanner.nextLine();
        }

        players.forEach((name, cards) -> {
            int points = calculatePoints(cards);
            System.out.printf("%s: %d\n", name, points);
        });
    }

    private static int calculatePoints(HashSet<String> cards) {

        int points = 0;
        for (String card : cards) {
            String power = card.substring(0, card.length() - 1);
            String type = card.substring(card.length() - 1);
            points += cardsPowers.get(power) * types.get(type);
        }
        return points;
    }
}