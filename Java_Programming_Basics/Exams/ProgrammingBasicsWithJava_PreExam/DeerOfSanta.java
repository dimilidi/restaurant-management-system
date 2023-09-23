package Exams.ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class DeerOfSanta {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int leftFood = Integer.parseInt(scanner.nextLine());
        double foodDeer1 = Double.parseDouble(scanner.nextLine());
        double foodDeer2 = Double.parseDouble(scanner.nextLine());
        double foodDeer3 = Double.parseDouble(scanner.nextLine());

        double totalFoodNeeded = (days * foodDeer1) + (days * foodDeer2) + (days * foodDeer3);

        if (totalFoodNeeded <= leftFood) {
            double foodLeft = Math.floor(leftFood - totalFoodNeeded);
            System.out.printf("%.0f kilos of food left.", foodLeft);
        } else {
            double moreFoodNeeded = Math.ceil(totalFoodNeeded - leftFood);
            System.out.printf("%.0f more kilos of food are needed.", moreFoodNeeded);
        }
    }
}
