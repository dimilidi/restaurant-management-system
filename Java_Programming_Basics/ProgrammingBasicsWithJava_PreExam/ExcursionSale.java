package ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class ExcursionSale {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int seaExcursions = Integer.parseInt(scanner.nextLine());
        int mountainExcursions = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int totalPrice = 0;

        while (!input.equals("Stop")) {
            if (input.equals("sea")) {
                if (seaExcursions > 0) {
                    totalPrice += 680;
                    seaExcursions--;
                    if (seaExcursions == 0 && mountainExcursions == 0) {
                        break;
                    }
                }
            } else if (input.equals("mountain")) {
                if (mountainExcursions > 0) {
                    totalPrice += 499;
                    mountainExcursions--;
                    if (seaExcursions == 0 && mountainExcursions == 0) {
                        break;
                    }
                }
            }

            input = scanner.nextLine();
        }

        if (seaExcursions == 0 && mountainExcursions == 0) {
            System.out.println("Good job! Everything is sold.");
        }

        System.out.printf("Profit: %d leva.", totalPrice);
    }
}
