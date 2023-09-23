package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class BeerKegs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countBeerKegs = Integer.parseInt(scanner.nextLine());
        String model = "";
        double max = 0.0;
        String biggestKeg = "";

        for (int i = 0; i < countBeerKegs; i++) {
            model = scanner.nextLine();
            double radius = Double.parseDouble(scanner.nextLine());
            int height = Integer.parseInt(scanner.nextLine());
            double volume = Math.PI * Math.pow(radius, 2) * height;
            max = Math.max(max, volume);

            if (max == volume) {
                biggestKeg = model;
            }
        }
        System.out.println(biggestKeg);
    }
}
