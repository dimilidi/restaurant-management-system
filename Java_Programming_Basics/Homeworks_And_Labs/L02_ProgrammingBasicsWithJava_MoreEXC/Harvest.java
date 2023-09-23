package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class Harvest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int area = Integer.parseInt(scanner.nextLine());
        double grapesKg = Double.parseDouble(scanner.nextLine());
        int wineLiterNeeded = Integer.parseInt(scanner.nextLine());
        int workers = Integer.parseInt(scanner.nextLine());

        double harvest = area * grapesKg;
        double wineProduced = harvest * 0.4 / 2.5;

        if(wineProduced < wineLiterNeeded) {
            double diff = Math.floor(wineLiterNeeded - wineProduced);
            System.out.printf("It will be a tough winter! More %.0f liters wine needed.", diff);
        } else {
            double diff = Math.ceil(Math.abs(wineProduced - wineLiterNeeded));
            double winePerWorker = Math.ceil(diff / workers);
            System.out.printf("Good harvest this year! Total wine: %.0f liters.%n", Math.floor(wineProduced));
            System.out.printf("%.0f liters left -> %.0f liters per person.", diff, winePerWorker);
        }
    }
}
