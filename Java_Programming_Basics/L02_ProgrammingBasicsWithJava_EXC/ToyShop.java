package L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class ToyShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double excursionPrice = Double.parseDouble(scanner.nextLine());
        int puzzlesCount = Integer.parseInt(scanner.nextLine());
        int talkingDollsCount = Integer.parseInt(scanner.nextLine());
        int teddiesCount = Integer.parseInt(scanner.nextLine());
        int minionsCount = Integer.parseInt(scanner.nextLine());
        int lorriesCount = Integer.parseInt(scanner.nextLine());

        double puzzlesPrice = puzzlesCount * 2.60;
        double talkingDollsPrice = talkingDollsCount * 3;
        double teddiesPrice = teddiesCount * 4.10;
        double minionsPrice = minionsCount * 8.20;
        double lorriesPrice = lorriesCount * 2;

        int totalToys = puzzlesCount + talkingDollsCount + teddiesCount + minionsCount + lorriesCount;
        double totalOrderPrice = puzzlesPrice + talkingDollsPrice + teddiesPrice + minionsPrice + lorriesPrice;

        if( totalToys >= 50) {
            totalOrderPrice = totalOrderPrice  - (totalOrderPrice * 0.25);
        } else  {
             totalOrderPrice = totalOrderPrice;
        }

        double profit = totalOrderPrice - (totalOrderPrice * 0.1);
        double profitExcursionDifference = profit - excursionPrice;

        if(profit >= excursionPrice) {
            System.out.printf("Yes! %.2f lv left.", profitExcursionDifference);
        } else {
            System.out.printf("Not enough money! %.2f lv needed.", profitExcursionDifference * -1);
        }
    }
}
