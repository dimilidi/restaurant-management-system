package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.FoodShortage;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Buyer> buyersByName = new HashMap<>();

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            if (input.length == 4) {
                Citizen citizen = new Citizen(input[0], Integer.parseInt(input[1]), input[2], input[3]);
                buyersByName.put(citizen.getName(), citizen);
            } else if (input.length == 3) {
                Rebel rebel = new Rebel(input[0], Integer.parseInt(input[1]), input[2]);
                buyersByName.put(rebel.getName(), rebel);
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("End")) {
            Buyer buyer = buyersByName.get(command);
            if (buyer != null) {
                buyer.buyFood();
            }
            command = scanner.nextLine();
        }

        int totalFood = 0;
        for(Buyer buyer : buyersByName.values()) {
            totalFood += buyer.getFood();
        }

        System.out.printf("%d%n", totalFood);
    }
}

