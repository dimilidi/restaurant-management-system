package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.Ferrari;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        Ferrari ferrari = new Ferrari(name);
        System.out.printf("%s%n",ferrari);
    }
}
