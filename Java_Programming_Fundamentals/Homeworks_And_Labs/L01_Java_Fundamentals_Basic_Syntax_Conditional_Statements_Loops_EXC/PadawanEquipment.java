package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class PadawanEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double  moneyAvailable = Double.parseDouble(scanner.nextLine());
        int studentsCount = Integer.parseInt(scanner.nextLine());
        double singlePriceLightsaber = Double.parseDouble(scanner.nextLine());
        double singlePriceRobe = Double.parseDouble(scanner.nextLine());
        double singlePriceBelt = Double.parseDouble(scanner.nextLine());

        double lightsabersCount = Math.ceil(studentsCount + (studentsCount * 0.1));

        double lightsabersPrice = lightsabersCount * singlePriceLightsaber;
        double robePrice = studentsCount * singlePriceRobe;
        double beltPrice = (studentsCount  - studentsCount / 6) * singlePriceBelt;

        double moneyNeeded  = lightsabersPrice + robePrice + beltPrice;

        if(moneyNeeded <= moneyAvailable) {
            System.out.printf("The money is enough - it would cost %.2flv.", moneyNeeded);
        } else {
            System.out.printf("George Lucas will need %.2flv more.",  moneyNeeded - moneyAvailable);
        }
    }
}
