package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.HotelReservation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = scanner.nextLine().split("\\s+");

        double pricePerDay = Double.parseDouble(tokens[0]);
        int days = Integer.parseInt(tokens[1]);
        Season season = Season.valueOf(tokens[2].toUpperCase());
        DiscountType discountType = DiscountType.fromString(tokens[3]);

        System.out.printf("%.2f", PriceCalculator.calculatePrice(pricePerDay, days, season, discountType));
    }
}
