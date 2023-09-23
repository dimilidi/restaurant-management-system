package Homeworks_And_Labs.L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class CinemaTickets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String movie = scanner.nextLine();
        int totalSeats = 0;
        int seatsStudent = 0;
        int seatsStandard = 0;
        int seatsKids = 0;

        while (!movie.equalsIgnoreCase("finish")){

            int freeSeats = Integer.parseInt(scanner.nextLine());

            String ticketType = "";
            double percentFull = 0.0;
            int seatsTaken = 0;

            for (int i = 0; i < freeSeats; i++) {
                ticketType = scanner.nextLine();
                if (ticketType.equalsIgnoreCase("end")) {
                    break;
                }

                if (ticketType.equalsIgnoreCase("student")) {
                    seatsStudent++;
                }
                if (ticketType.equalsIgnoreCase("standard")) {
                    seatsStandard++;
                }
                if (ticketType.equalsIgnoreCase("kid")) {
                    seatsKids++;
                }
                seatsTaken++;
            }

            totalSeats += seatsTaken;
            percentFull = (seatsTaken * 100.0) /  freeSeats ;

            System.out.printf("%s - %.2f%% full.%n", movie, percentFull);
            movie = scanner.nextLine();
        }

        double studentSeatsPercentage =  (seatsStudent * 100.0) / totalSeats;
        double standardSeatsPercentage =  (seatsStandard * 100.0) / totalSeats;
        double kidsSeatsPercentage =  (seatsKids * 100.0) / totalSeats;

        System.out.printf("Total tickets: %d%n", totalSeats);
        System.out.printf("%.2f%% student tickets.%n", studentSeatsPercentage);
        System.out.printf("%.2f%% standard tickets.%n", standardSeatsPercentage );
        System.out.printf("%.2f%% kids tickets.%n", kidsSeatsPercentage);
    }
}
