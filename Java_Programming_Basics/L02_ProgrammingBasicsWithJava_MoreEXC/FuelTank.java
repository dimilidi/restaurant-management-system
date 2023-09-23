package L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class FuelTank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String fuelType = scanner.nextLine();
        double fuelLiters = Double.parseDouble(scanner.nextLine());

        if (!(fuelType.equals("Diesel") || fuelType.equals("Gas") || fuelType.equals("Gasoline"))) {
            System.out.println("Invalid fuel!");
        } else {
            if (fuelLiters < 25){
                System.out.printf("Fill your tank with %s!", fuelType.toLowerCase());
            } else if(fuelLiters >= 25) {
                System.out.printf("You have enough %s.", fuelType.toLowerCase());
            }
        }
    }
}
