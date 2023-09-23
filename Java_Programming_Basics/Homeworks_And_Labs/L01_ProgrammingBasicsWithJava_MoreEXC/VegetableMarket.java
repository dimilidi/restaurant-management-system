package Homeworks_And_Labs.L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class VegetableMarket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double kgVegetablePrice = Double.parseDouble(scanner.nextLine());
        double kgFruitPrice = Double.parseDouble(scanner.nextLine());
        int totalKgVegetable = Integer.parseInt(scanner.nextLine());
        int totalKgFruit = Integer.parseInt(scanner.nextLine());

        double totalIncomeInEuro = ((totalKgVegetable * kgVegetablePrice) + (totalKgFruit * kgFruitPrice)) / 1.94;
        System.out.printf("%.2f", totalIncomeInEuro);
    }
}
