package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class SnowBalls {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberSnowballs = Integer.parseInt(scanner.nextLine());
        double maxSnowball = Double.MIN_VALUE;
        String output = "";

        for (int i = 0; i < numberSnowballs; i++) {
            int snowballSnow = Integer.parseInt(scanner.nextLine());
            int snowballTime = Integer.parseInt(scanner.nextLine());
            int snowballQuality = Integer.parseInt(scanner.nextLine());

            double snowballValue = Math.pow(snowballSnow / snowballTime, snowballQuality);

            maxSnowball = Math.max(snowballValue, maxSnowball);

            if(maxSnowball == snowballValue) {
                output = String.format("%d : %d = %.0f (%d)", snowballSnow, snowballTime,  snowballValue, snowballQuality);
            }
        }
        System.out.println(output);
    }
}



