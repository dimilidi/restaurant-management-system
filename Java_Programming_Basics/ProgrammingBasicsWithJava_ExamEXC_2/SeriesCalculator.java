package ProgrammingBasicsWithJava_ExamEXC_2;

import java.util.Scanner;

public class SeriesCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String movie = scanner.nextLine();
        int seasons = Integer.parseInt(scanner.nextLine());
        int episodes = Integer.parseInt(scanner.nextLine());
        double episodeDuration = Double.parseDouble(scanner.nextLine());

        double episodeWithAd = (episodeDuration * 0.2) + episodeDuration;
        double totalTime = Math.floor((episodeWithAd * episodes * seasons ) + 10 * seasons );

        System.out.printf("Total time needed to watch the %s series is %.0f minutes.", movie, totalTime);
    }
}
