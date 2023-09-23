package Homeworks_And_Labs.L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class Fishland {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double pricePerKgMackerel = Double.parseDouble(scanner.nextLine());
        double pricePerKgSprat = Double.parseDouble(scanner.nextLine());
        double kgBonito = Double.parseDouble(scanner.nextLine());
        double kgHorseMackerel = Double.parseDouble(scanner.nextLine());
        int kgClam = Integer.parseInt(scanner.nextLine());

        double priceBonito = kgBonito * (pricePerKgMackerel + (pricePerKgMackerel * 0.6));
        double priceHorseMackerel = kgHorseMackerel * (pricePerKgSprat + (pricePerKgSprat * 0.8));
        double priceClam = kgClam * 7.5;

        double totalPrice = priceBonito + priceHorseMackerel + priceClam;

        System.out.printf("%.2f",totalPrice );

    }
}
