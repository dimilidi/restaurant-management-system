package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_LAB;

import java.util.Scanner;

public class DayOfWeek {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int index = Integer.parseInt(scanner.nextLine());

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        String result = "";

        if(index > 0 && index <= daysOfWeek.length){
            result = daysOfWeek[index - 1];
        } else {
            result = "Invalid day!";
        }

        System.out.println(result);
    }
}
