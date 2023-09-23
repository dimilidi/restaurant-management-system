package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class FishingBoat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int budget = Integer.parseInt(scanner.nextLine());
        String season = scanner.nextLine().toLowerCase();
        int fishmenCount = Integer.parseInt(scanner.nextLine());
        double boatRentPrice = 0.0;

        if (season.equals("spring")){
            boatRentPrice = 3000;
        } else if (season.equals("summer") || season.equals("autumn")) {
            boatRentPrice = 4200;
        } else if(season.equals("winter")) {
            boatRentPrice = 2600;
        }

        if (fishmenCount <= 6) {
            boatRentPrice = boatRentPrice - (boatRentPrice * 0.1);
        } else if (fishmenCount >= 7 && fishmenCount <= 11) {
            boatRentPrice = boatRentPrice - (boatRentPrice * 0.15);
        } else if (fishmenCount > 12 ) {
            boatRentPrice = boatRentPrice - (boatRentPrice * 0.25);
        }

        if((fishmenCount % 2 == 0) && !(season.equals("autumn"))) {
            boatRentPrice = boatRentPrice - (boatRentPrice * 0.05);
        } else {
            boatRentPrice = boatRentPrice;
        }

        double leftMoney = Math.abs(budget - boatRentPrice);

        if (budget >= boatRentPrice) {
            System.out.printf("Yes! You have %.2f leva left.", leftMoney);
        } else {
            System.out.printf("Not enough money! You need %.2f leva.", leftMoney);
        }

    }
}
