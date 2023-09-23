package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class SpiceMustFlow {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int yield = Integer.parseInt(scanner.nextLine());
        int days = 0;
        int totalAmount = 0;

        while (yield >= 100) {
            days++;
            totalAmount += yield -26;
            yield -=10;
        }
        if (totalAmount >= 26) {
            totalAmount -= 26;
        }

        System.out.println(days);
        System.out.println(totalAmount);
    }
}
