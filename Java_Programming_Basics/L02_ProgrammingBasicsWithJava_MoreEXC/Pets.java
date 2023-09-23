package L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class Pets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        int leftFoodKg = Integer.parseInt(scanner.nextLine());
        double dogFoodKgDay = Double.parseDouble(scanner.nextLine());
        double catFoodKgDay = Double.parseDouble(scanner.nextLine());
        double turtleFoodGramDay = Double.parseDouble(scanner.nextLine());

        double totalFood = (dogFoodKgDay + catFoodKgDay + turtleFoodGramDay / 1000) * days;

        if(leftFoodKg > totalFood) {
            double diff = Math.floor(leftFoodKg - totalFood);
            System.out.printf("%.0f kolis of food left.", diff);
        } else {
            double diff = Math.ceil(totalFood - leftFoodKg);
            System.out.printf("%.0f more kilos of food are needed.", diff);
        }
    }
}
