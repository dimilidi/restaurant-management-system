package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class GodzillaVsKong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double filmBudget = Double.parseDouble(scanner.nextLine());
        int statistsCount = Integer.parseInt(scanner.nextLine());
        double clothesPricePerStatist = Double.parseDouble(scanner.nextLine());
        double clothesPrice = clothesPricePerStatist * statistsCount;

        double decorPrice = filmBudget * 0.1;

        if( statistsCount > 150) {
            clothesPrice = clothesPrice - (clothesPrice * 0.1);
        }

        double neededMoney = clothesPrice + decorPrice;
        double differenceFilmBudgetNeededMoney = filmBudget - neededMoney;

        if(neededMoney > filmBudget) {
            System.out.printf("Not enough money! %n Wingard needs %.2f leva more.", differenceFilmBudgetNeededMoney * -1);
        } else if (neededMoney <= filmBudget) {
            System.out.printf("Action! %n Wingard starts filming with %.2f leva left.", differenceFilmBudgetNeededMoney);
        }
    }
}
