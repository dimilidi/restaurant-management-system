package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Journey {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double budget = Double.parseDouble(scanner.nextLine());
        String season = scanner.nextLine().toLowerCase();
        String tripDestination = "";
        double price = 0.00;
        String accommodation = "Hotel";

       if(budget <= 100) {
           tripDestination = "Bulgaria";
           if(season.equals("summer")) {
               price = budget * 0.3;
               accommodation = "Camp";
           } else {
               price = budget * 0.7;
               accommodation = "Hotel";
           }
       } else if (budget <= 1000) {
           tripDestination = "Balkans";
           if(season.equals("summer")) {
               price = budget * 0.4;
               accommodation = "Camp";
           } else {
               price = budget * 0.8;
               accommodation = "Hotel";
           }
       } else {
           tripDestination = "Europe";
               price = budget * 0.9;
               accommodation = "Hotel";
       }

        System.out.printf("Somewhere in %s %n%s - %.2f", tripDestination, accommodation, price);
    }
}
