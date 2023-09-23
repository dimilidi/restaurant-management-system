package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class SleepyTomCat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysOff = Integer.parseInt(scanner.nextLine());

        int workingDays = 365 - daysOff;
        int playMinutesWorkingDays = workingDays * 63;
        int playMinutesDaysOff = daysOff * 127;
        int playMinutesTotal = playMinutesDaysOff + playMinutesWorkingDays;
        int playTimeNormTotalPlayMinutesDifference =  Math.abs(30000 - playMinutesTotal);
        int playTimeNormTotalPlayMinutesDifferenceHours = playTimeNormTotalPlayMinutesDifference / 60;
        int playTimeNormTotalPlayMinutesDifferenceMinutes = playTimeNormTotalPlayMinutesDifference % 60;

        if(playMinutesTotal > 30000) {
            System.out.printf("Tom will run away %n%d hours and %d minutes more for play",playTimeNormTotalPlayMinutesDifferenceHours, playTimeNormTotalPlayMinutesDifferenceMinutes, playTimeNormTotalPlayMinutesDifference);
        } else if (playMinutesTotal <= 30000) {
            System.out.printf("Tom sleeps well %n%d hours and %d minutes less for play",playTimeNormTotalPlayMinutesDifferenceHours, playTimeNormTotalPlayMinutesDifferenceMinutes, playTimeNormTotalPlayMinutesDifference);


        }
    }
}
