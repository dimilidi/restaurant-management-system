package Homeworks_And_Labs.L06_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class Travelling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String destination = scanner.nextLine();

        while(!destination.equalsIgnoreCase(("end"))) {
            double moneyNeeded = Double.parseDouble(scanner.nextLine());;
            double moneySavedTotal =  0.0;

            while(moneySavedTotal < moneyNeeded) {
                double moneySaved = Double.parseDouble(scanner.nextLine());
                moneySavedTotal += moneySaved;
            }

            System.out.printf("Going to %s!%n", destination);
            destination = scanner.nextLine();
        }
    }
}
