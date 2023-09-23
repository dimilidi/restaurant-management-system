package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class WaterOverflow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int waterTankCapacity = 255;
        int countInputLines = Integer.parseInt(scanner.nextLine());

        int totalWater = 0;

        for (int i = 0; i < countInputLines; i++) {
            int water = Integer.parseInt(scanner.nextLine());

            if (waterTankCapacity >= totalWater + water) {
                totalWater += water;
            } else {
                System.out.println("Insufficient capacity!");
            }
        }

        System.out.println(totalWater);
    }
}
