package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class LowerOrUpper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String character = scanner.nextLine();

        if(character.toLowerCase().equals(character)){
            System.out.println("lower-case");
        } else {
            System.out.println("upper-case");
        }

    }
}
