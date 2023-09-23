package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class TownInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String nameTown = scanner.nextLine();
        long population = Integer.parseInt(scanner.nextLine());
        int area = Integer.parseInt(scanner.nextLine());

        System.out.printf("Town %s has population of %d and area %d square km.", nameTown,population, area );
    }
}
