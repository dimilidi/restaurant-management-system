package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class TennisRanklist {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        int points = Integer.parseInt(scanner.nextLine());
        double totalPoints = points;

        double f = 0;
        double sf = 0;
        double w = 0;

        for (int i = 0; i < count; i++) {
            String tournamentStage = scanner.nextLine();

            if (tournamentStage.equals("F")) {
                totalPoints += 1200;
                f++;
            } else if (tournamentStage.equals("SF")) {
                totalPoints += 720;
                sf++;
            } else if (tournamentStage.equals("W")) {
                totalPoints += 2000;
                w++;
            }
        }

        double  pointsAvrg = Math.floor((totalPoints - points) / count);
        double percentWin = (w / count) * 100;

        System.out.printf("Final points: %.0f%n", totalPoints);
        System.out.printf("Average points: %.0f%n", pointsAvrg);
        System.out.printf("%.2f%%", percentWin);
    }
}
