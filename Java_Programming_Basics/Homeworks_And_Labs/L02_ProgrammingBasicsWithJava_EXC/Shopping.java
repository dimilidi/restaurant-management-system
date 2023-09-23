package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Shopping {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double budgetOfPeter = Double.parseDouble(scanner.nextLine());
        int countVideoCard = Integer.parseInt(scanner.nextLine());
        int countProcessor = Integer.parseInt(scanner.nextLine());
        int countRAM = Integer.parseInt(scanner.nextLine());

        double priceVideoCard = countVideoCard * 250;
        double priceProcessor = (priceVideoCard * 0.35) * countProcessor;
        double priceRAM = (priceVideoCard * 0.1) * countRAM;

        double totalPrice = priceVideoCard + priceProcessor + priceRAM;

        if (countVideoCard > countProcessor) {
            totalPrice = totalPrice - (totalPrice * 0.15);
        }

        double differenceTotalPriceBudget = budgetOfPeter - totalPrice;

        if(budgetOfPeter >= totalPrice){
            System.out.printf("You have %.2f leva left!",differenceTotalPriceBudget);
        } else {
            System.out.printf("Not enough money! You need %.2f leva more!",differenceTotalPriceBudget * -1);
        }

    }
}
