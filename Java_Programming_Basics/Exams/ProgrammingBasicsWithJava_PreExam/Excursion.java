package Exams.ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class Excursion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int participants = Integer.parseInt(scanner.nextLine());
        int nights = Integer.parseInt(scanner.nextLine());
        int transportCards = Integer.parseInt(scanner.nextLine());
        int musеumCards = Integer.parseInt(scanner.nextLine());


        double nightsPrice = nights * 20.0;
        double transportCardsPrice = transportCards * 1.60;
        double museumCardsPrice = musеumCards * 6.0;
        double sum = (nightsPrice + transportCardsPrice + museumCardsPrice) * participants;
        double totalSum = sum + sum * 0.25;

        System.out.printf("%.2f", totalSum);
    }
}
