package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class HotelRoom {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String month = scanner.nextLine().toLowerCase();
        int stayCount = Integer.parseInt(scanner.nextLine());
        double apartmentPricePerNight = 0.00;
        double studioPricePerNight = 0.00;


        if(month.equals("may") || month.equals("october")) {
            studioPricePerNight = 50.00;
            apartmentPricePerNight = 65.00;
            if (stayCount > 14) {
                studioPricePerNight *= 0.7;
                apartmentPricePerNight *= 0.9;
            } else if (stayCount > 7) {
                studioPricePerNight *= 0.95;
            }
        } else if (month.equals("june") || month.equals("september")) {
            studioPricePerNight = 75.20;
            apartmentPricePerNight = 68.70;
            if (stayCount > 14) {
                studioPricePerNight *= 0.8;
                apartmentPricePerNight *= 0.9;
            }
        } else if (month.equals("july") || month.equals("august")) {
            studioPricePerNight = 76.00;
            apartmentPricePerNight = 77.00;
            if (stayCount > 14) {
                apartmentPricePerNight *= 0.9;
            }
        }

        double totalPriceStudio = stayCount * studioPricePerNight;
        double totalPriceApartment = stayCount * apartmentPricePerNight;
        System.out.printf("Apartment: %.2f lv.%n", totalPriceApartment);
        System.out.printf("Studio: %.2f lv.%n", totalPriceStudio);
    }
}
