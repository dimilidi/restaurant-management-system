package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.AnimalFarm;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int age = Integer.parseInt(scanner.nextLine());

        try{
            Chicken chicken = new Chicken(name, age);
            System.out.printf("%s%n", chicken);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
