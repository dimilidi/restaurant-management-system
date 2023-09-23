package L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class NewHouse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String flowerType = scanner.nextLine().toLowerCase();
        int flowerCount = Integer.parseInt(scanner.nextLine());
        int budget = Integer.parseInt(scanner.nextLine());
        double flowerPrice = 0.00;

        switch(flowerType) {
            case "roses":
                flowerPrice = 5;
                break;
            case "dahlias":
                flowerPrice = 3.80;
                break;
            case "tulips":
                flowerPrice = 2.80;
                break;
            case "narcissus":
                flowerPrice = 3;
                break;
            case "gladiolus":
                flowerPrice = 2.50;
                break;
            default:
                System.out.println("Invalid flower type.");
                break;
        }

        double totalPrice = flowerPrice * flowerCount;


        if(flowerType.equals("roses") && flowerCount > 80) {
            totalPrice = totalPrice - (totalPrice * 0.1);
        } else if (flowerType.equals("dahlias") && flowerCount > 90) {
            totalPrice = totalPrice - (totalPrice * 0.15);
        } else if (flowerType.equals("tulips") && flowerCount > 80) {
            totalPrice = totalPrice - (totalPrice * 0.15);
        } else if (flowerType.equals("narcissus") && flowerCount < 120) {
            totalPrice = totalPrice + (totalPrice * 0.15);
        } else if (flowerType.equals("gladiolus") && flowerCount < 80) {
            totalPrice = totalPrice + (totalPrice * 0.2);
        } else {
            totalPrice = flowerPrice * flowerCount;
        }

        double totalPriceBudgetDifference = Math.abs(budget - totalPrice);
        String firstLetterCapitalizedFlowerType = flowerType.substring(0, 1).toUpperCase() + flowerType.substring(1);

        if(budget >= totalPrice) {
            System.out.printf("Hey, you have a great garden with %d %s and %.2f leva left.", flowerCount, firstLetterCapitalizedFlowerType,totalPriceBudgetDifference );
        } else {
            System.out.printf("Not enough money, you need %.2f leva more.", totalPriceBudgetDifference);
        }


    }
}
