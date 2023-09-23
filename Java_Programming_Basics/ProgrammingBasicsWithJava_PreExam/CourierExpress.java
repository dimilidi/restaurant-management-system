package ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class CourierExpress {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double weight = Double.parseDouble(scanner.nextLine());;
        String type = scanner.nextLine();
        int distance = Integer.parseInt(scanner.nextLine());;

        double price = 0.0;

        if(type.equals("standard")) {
            if (weight < 1) {
                price = distance * 0.03;
            } else if (weight >= 1 && weight < 10) {
                price = distance * 0.05;
            } else if (weight >= 10 && weight < 40) {
                price = distance * 0.10;
            } else if (weight >= 40 && weight < 90) {
                price = distance * 0.15;
            } else if (weight >= 90 && weight < 150) {
                price = distance * 0.20;
            }
        } else if (type.equals("express")) {
            if (weight < 1) {
                price = distance * 0.03 + (weight * distance * 0.03 * 0.8);
            } else if (weight >= 1 && weight < 10) {
                price = distance * 0.05 + (weight * distance * 0.05 * 0.4);
            } else if (weight >= 10 && weight < 40) {
                price = distance * 0.10 + (weight * distance * 0.10 * 0.05);
            } else if (weight >= 40 && weight < 90) {
                price = distance * 0.15 + (weight * distance * 0.15 * 0.02);
            } else if (weight >= 90 && weight < 150) {
                price = distance * 0.20 + (weight * distance * 0.20 * 0.01);
            }
        }

        System.out.printf("The delivery of your shipment with weight of %.3f kg. would cost %.2f lv.", weight, price);
    }
}
