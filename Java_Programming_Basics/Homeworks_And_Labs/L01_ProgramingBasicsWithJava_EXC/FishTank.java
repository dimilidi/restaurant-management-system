package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class FishTank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = Integer.parseInt(scanner.nextLine());
        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        double percent = Double.parseDouble(scanner.nextLine());
        double volume = length * width * height;
        double volumeInLiters = volume / 1000;
        double litersNeeded = volumeInLiters - (volumeInLiters * percent / 100);
        System.out.println(litersNeeded);


    }
}
