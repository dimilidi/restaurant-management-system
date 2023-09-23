package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;


public class CleverLily {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = Integer.parseInt(scanner.nextLine());
        double priceWashingMachine = Double.parseDouble(scanner.nextLine());
        int priceToy = Integer.parseInt(scanner.nextLine());
        double money = 0.0;
        double totalMoney = 0.0;
        int toysCount = 0;

        for (int i = 1; i <= age; i++) {
            if (i % 2 == 0) {
                money += 10.0;
                totalMoney += money - 1.0; // Brother takes 1.0 from each even-aged birthday
            } else {
                toysCount++;
            }
        }

        double toysMoney = toysCount * priceToy;
        totalMoney += toysMoney;

        if (totalMoney >= priceWashingMachine) {
            double diff = totalMoney - priceWashingMachine;
            System.out.printf("Yes! %.2f", diff);
        } else {
            double diff = priceWashingMachine - totalMoney;
            System.out.printf("No! %.2f", diff);
        }
    }
}
