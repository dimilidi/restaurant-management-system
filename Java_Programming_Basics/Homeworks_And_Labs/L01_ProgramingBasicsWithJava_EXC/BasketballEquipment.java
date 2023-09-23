package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class BasketballEquipment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double tax = Double.parseDouble(scanner.nextLine());
        double shoes = tax - (tax * 40 / 100);
        double clothes = shoes - (shoes * 20 / 100);
        double ball = clothes / 4;
        double accessory = ball / 5;
        double totalExpenses = tax + shoes + clothes + ball + accessory;
        System.out.println(totalExpenses);
    }
}
