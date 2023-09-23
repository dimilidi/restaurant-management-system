package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class CinemaTicket {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String day = scanner.nextLine().toLowerCase();
        int ticketPrice = 0;

        switch(day) {
            case "monday":
            case "tuesday":
            case "friday":
                ticketPrice = 12;
                break;
            case "wednesday":
            case "thursday":
                ticketPrice = 14;
                break;
            case "saturday":
            case "sunday":
                ticketPrice = 16;
                break;
            default:
                ticketPrice = 0;
        }

        System.out.println(ticketPrice);
    }
}
