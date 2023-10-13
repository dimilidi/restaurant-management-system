package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class SoftUniExamResults {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> participants = new LinkedHashMap<>();
        Map<String, Integer> submissionsCount = new LinkedHashMap<>();

        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equals("exam finished")) {
                break;
            }

            String[] tokens = input.split("-");
            String username = tokens[0];
            String language = tokens[1];

            if (language.equals("banned")) {
                // remove username, but preserve points
                if (participants.containsKey(username)) {
                    participants.remove(username);
                }
            } else {
                int points = Integer.parseInt(tokens[2]);
                // save user with their highest points
                participants.put(username, Math.max(participants.getOrDefault(username, 0), points));

                if (!submissionsCount.containsKey(language)) {
                    submissionsCount.put(language, 1);
                } else {
                    submissionsCount.put(language, submissionsCount.get(language) + 1);
                }
            }
        }

        System.out.println("Results:");
        participants.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " | " + entry.getValue()));

        System.out.println("Submissions:");
        submissionsCount.entrySet().stream()
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }

}
