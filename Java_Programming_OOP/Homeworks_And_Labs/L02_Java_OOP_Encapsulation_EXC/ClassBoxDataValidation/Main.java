package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.ClassBoxDataValidation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double length = Double.parseDouble(scanner.nextLine());
        double width = Double.parseDouble(scanner.nextLine());
        double height = Double.parseDouble(scanner.nextLine());

        try {
            Box box = new Box(length, width, height);
            box.print();
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

    }
}
