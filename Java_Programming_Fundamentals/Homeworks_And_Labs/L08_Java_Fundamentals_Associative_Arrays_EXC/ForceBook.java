package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class ForceBook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        // Use a map to store Force sides and their respective users.
        Map<String, List<String>> forceSides = new LinkedHashMap<>();

        String input = scanner.nextLine();

        while (!input.equals("Lumpawaroo")) {
            if (input.contains(" | ")) {
                // Split the input when a new user joins a side.
                String[] tokens = input.split(" \\| ");
                String forceSide = tokens[0];
                String forceUser = tokens[1];

                // Create the side if it doesn't exist.
                forceSides.putIfAbsent(forceSide, new ArrayList<>());

                // If the user is not already on any side, add them to the specified side.
                if (!isUserOnAnySide(forceUser, forceSides)) {
                    forceSides.get(forceSide).add(forceUser);
                }
            } else if (input.contains(" -> ")) {
                // Split the input when a user changes sides.
                String[] tokens = input.split(" -> ");
                String forceUser = tokens[0];
                String forceSide = tokens[1];

                // If the user was on any side, remove them.
                if (isUserOnAnySide(forceUser, forceSides)) {
                    for (Map.Entry<String, List<String>> entry : forceSides.entrySet()) {
                        entry.getValue().remove(forceUser);
                    }
                }

                // Create the side if it doesn't exist and add the user to it.
                forceSides.putIfAbsent(forceSide, new ArrayList<>());
                forceSides.get(forceSide).add(forceUser);

                System.out.println(forceUser + " joins the " + forceSide + " side!");
            }

            input = scanner.nextLine();
        }

        // Print the Force sides with their members.
        forceSides.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 0)
                .forEach(entry -> {
                    System.out.println("Side: " + entry.getKey() + ", Members: " + entry.getValue().size());
                    entry.getValue().forEach(user -> System.out.println("! " + user));
                });
    }

    // Check if a user is on any Force side.
    private static boolean isUserOnAnySide(String forceUser, Map<String, List<String>> forceSides) {
        for (List<String> users : forceSides.values()) {
            if (users.contains(forceUser)) {
                return true;
            }
        }
        return false;
    }
}