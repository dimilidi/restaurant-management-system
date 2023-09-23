package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class AccountBalance {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        double total = 0.0;

        while(!(input.equalsIgnoreCase("NoMoreMoney"))) {
            input = scanner.nextLine();

            if (input.equalsIgnoreCase("NoMoreMoney")) {
                break;
            }

            double money = Double.parseDouble(input);

            if(money > 0) {
                System.out.printf("Increase: %.2f%n", money);
                total += money;
            } else {
                System.out.println("Invalid operation!");
                break;
            }
        }
        System.out.printf("Total: %.2f", total);
    }
}
