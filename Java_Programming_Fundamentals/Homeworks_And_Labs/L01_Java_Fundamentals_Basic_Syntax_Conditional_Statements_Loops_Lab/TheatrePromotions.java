package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class TheatrePromotions {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String day = scanner.nextLine().toLowerCase();
        int age = Integer.parseInt(scanner.nextLine());
        double price = 0.0;
        String result = "";

      if(day.equals("weekday")) {
            if(0 <= age && age <= 18) {
                price = 12;
            } else if(18 < age && age <= 64) {
                price = 18;
            } else {
                price = 12;
            }
        } else if (day.equals("weekend")) {
            if(0 <= age && age <= 18) {
                price = 15;
            } else if(18 < age && age <= 64) {
                price = 20;
            } else {
                price = 15;
            }
        } else if (day.equals("holiday")) {
            if(0 <= age && age <= 18) {
                price = 5;
            } else if(18 < age && age <= 64) {
                price = 12;
            } else {
                price = 10;
            }
        }

        if(122 < age || age < 0 ) {
            result = "Error!";
        } else {
           // result = price+"$";
           result =  String.format("%.0f$", price);
        }

        System.out.println(result);

    }
}
