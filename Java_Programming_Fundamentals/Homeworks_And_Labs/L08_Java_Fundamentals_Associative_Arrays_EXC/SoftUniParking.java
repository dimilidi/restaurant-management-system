package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class SoftUniParking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int commandsCount = Integer.parseInt(scanner.nextLine());
        Map<String, String> parkingUsersMap = new LinkedHashMap<>();

        for (int i = 0; i < commandsCount; i++) {
            String[] commandArr = scanner.nextLine().split(" ");
            String command = commandArr[0];
            String user = commandArr[1];
            switch (command) {
                case "register":
                    String licenseNumber = commandArr[2];

                    if (parkingUsersMap.containsKey(user)) {
                        System.out.printf("ERROR: already registered with plate number %s%n", licenseNumber);
                    } else {
                        parkingUsersMap.put(user, licenseNumber);
                        System.out.printf("%s registered %s successfully%n", user, licenseNumber);
                    }
                    break;

                case "unregister":
                    if (!parkingUsersMap.containsKey(user)) {
                        System.out.printf("ERROR: user %s not found%n", user);
                    } else {
                        parkingUsersMap.remove(user);
                        System.out.printf("%s unregistered successfully%n", user);
                    }
                    break;
            }
        }

        for (Map.Entry<String, String> entry : parkingUsersMap.entrySet()) {
            System.out.printf("%s => %s%n", entry.getKey(), entry.getValue());
        }
    }
}
