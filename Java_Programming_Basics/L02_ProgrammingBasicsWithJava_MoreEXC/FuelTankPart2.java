package L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class FuelTankPart2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fuelType = scanner.nextLine();
        double fuelLiters = Double.parseDouble(scanner.nextLine());
        String hasClubCard = scanner.nextLine();
        double price = 0.00;


        switch (fuelType) {
            case "Gasoline":
                price = fuelLiters * 2.22;
                if (hasClubCard.equals("Yes")) {
                    price -= fuelLiters * 0.18;
                }
                break;
            case "Diesel":
                price = fuelLiters * 2.33;
                if (hasClubCard.equals("Yes")) {
                    price -= fuelLiters * 0.12;
                }
                break;
            case "Gas":
                price = fuelLiters * 0.93;
                if (hasClubCard.equals("Yes")) {
                    price -= fuelLiters * 0.08;
                }
                break;
        }

        if(fuelLiters >= 20 && fuelLiters <= 25) {
            price = price * 0.92;
        } else if (fuelLiters > 25) {
            price = price * 0.90;
        }
        System.out.printf("%.2f lv.", price);
    }
}
