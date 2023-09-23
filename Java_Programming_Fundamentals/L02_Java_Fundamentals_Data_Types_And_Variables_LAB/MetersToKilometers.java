package L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class MetersToKilometers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int meters = Integer.parseInt(scanner.nextLine());
        double kilometers = meters / 1000.0;

        System.out.printf("%.2f",kilometers);
    }
}
