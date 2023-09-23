package L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class WorldSwimmingRecord {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double recordInSeconds = Double.parseDouble(scanner.nextLine());
        double distanceInMeters = Double.parseDouble(scanner.nextLine());
        double secondsNeededForOneMeter = Double.parseDouble(scanner.nextLine());

        double secondsTotal = secondsNeededForOneMeter * distanceInMeters;
        double delay = Math.floor((distanceInMeters / 15)) * 12.5;
        double timeOfIvan = secondsTotal + delay;

        double differenceRecordTimeOfIvan = timeOfIvan - recordInSeconds;

        if(timeOfIvan < recordInSeconds) {
            System.out.printf("Yes, he succeeded! The new world record is %.2f seconds.", timeOfIvan);
        } else if (timeOfIvan >= recordInSeconds) {
            System.out.printf("No, he failed! He was %.2f seconds slower.", differenceRecordTimeOfIvan );
        }

    }
}
