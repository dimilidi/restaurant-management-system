package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double totalSum = 0;
        String start = scanner.nextLine();

        while (!start.equals("Start")) {
            double coins = Double.parseDouble(start);

            if (coins == 0.1 || coins == 0.2 || coins == 0.5 || coins == 1.0 || coins == 2.0) {
                totalSum += coins;
            } else {
                System.out.printf("Cannot accept %.2f%n", coins);
            }

            start = scanner.nextLine();
        }

        double leftMoney = totalSum;
        boolean isValidProduct = false;

        while (true) {
            String product = scanner.nextLine();

            if (product.equals("End")) {
                break;
            }

            double price = 0.0;

            switch (product) {
                case "Nuts":
                    isValidProduct = true;
                    price = 2.0;
                    break;
                case "Water":
                    isValidProduct = true;
                    price = 0.7;
                    break;
                case "Crisps":
                    isValidProduct = true;
                    price = 1.5;
                    break;
                case "Soda":
                    isValidProduct = true;
                    price = 0.8;
                    break;
                case "Coke":
                    isValidProduct = true;
                    price = 1.0;
                    break;
                default:
                    System.out.println("Invalid product");
                    break;
            }

            if (isValidProduct) {
                if (leftMoney < price) {
                    System.out.println("Sorry, not enough money");
                } else {
                    System.out.printf("Purchased %s%n", product);
                    leftMoney -= price;
                }
            }
        }

        System.out.printf("Change: %.2f%n", leftMoney);
    }
}
