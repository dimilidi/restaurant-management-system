package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_LAB;

import java.util.Scanner;

public class PetShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dogsFoodCount = Integer.parseInt(scanner.nextLine());
        int catsFoodCount = Integer.parseInt(scanner.nextLine());

        double totalPrice = (catsFoodCount * 4 ) + (dogsFoodCount * 2.5);
        System.out.printf("%f lv", totalPrice);
    }
}
