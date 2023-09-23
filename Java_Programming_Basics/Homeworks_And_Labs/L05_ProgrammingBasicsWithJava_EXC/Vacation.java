package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Vacation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double moneyExcursion = Double.parseDouble(scanner.nextLine());
        double moneyHave = Double.parseDouble(scanner.nextLine());
        String action = scanner.nextLine();
        double moneyActive = Double.parseDouble(scanner.nextLine());

        int day = 0;
        int daySpending = 0;
        boolean isEnoughMoney = false;

        while(!isEnoughMoney) {
            day++;

            if(action.equalsIgnoreCase("spend")) {
                daySpending++;
                moneyHave -= moneyActive;
                if(moneyHave < 0) {
                    moneyHave = 0;
                }
            } else {
                daySpending = 0;
                moneyHave += moneyActive;
            }

            if (daySpending == 5) {
                break;
            }

            if(moneyHave >= moneyExcursion) {
                isEnoughMoney = true;
                break;
            }

            action = scanner.nextLine();
            moneyActive = Double.parseDouble(scanner.nextLine());
        }

        if(isEnoughMoney){
            System.out.printf("You saved the money for %d days.", day);
        } else {
            System.out.printf("You can't save the money.%n%d", day);
        }
    }
}
