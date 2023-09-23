package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countPeople = Integer.parseInt(scanner.nextLine());
        String groupType = scanner.nextLine().toLowerCase();
        String weekDay = scanner.nextLine().toLowerCase();
        double price = 0;

        switch (weekDay) {
            case "friday":
                if (groupType.equals("students")) {
                    price = 8.45;
                } else if (groupType.equals("business")) {
                    price = 10.90;
                } else if (groupType.equals("regular")) {
                    price = 15.00;
                }
                break;

            case "saturday":
                if (groupType.equals("students")) {
                    price = 9.80;
                } else if (groupType.equals("business")) {
                    price = 15.60;
                } else if (groupType.equals("regular")) {
                    price = 20.00;
                }
                break;

            case "sunday":
                if (groupType.equals("students")) {
                    price = 10.46;
                } else if (groupType.equals("business")) {
                    price = 16.00;
                } else if (groupType.equals("regular")) {
                    price = 22.50;
                }
                break;
        }

        double totalPrice = price * countPeople;

        if (groupType.equals("students") && countPeople >= 30) {
            totalPrice *= 0.85;
            ;
        } else if (groupType.equals("business") && countPeople >= 100) {
            totalPrice -= 10 * price;
        } else if (groupType.equals("regular") && countPeople >= 10 && countPeople <= 20) {
            totalPrice *= 0.95;
        }


        System.out.printf("Total price: %.2f", totalPrice);
    }
}

