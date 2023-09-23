package ProgrammingBasicsWithJava_ExamEXC_2;

import java.util.Scanner;

public class Skeleton {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int min = Integer.parseInt(scanner.nextLine());
        int sec = Integer.parseInt(scanner.nextLine());
        double lengthMeters = Double.parseDouble(scanner.nextLine());
        int secPer100Meter = Integer.parseInt(scanner.nextLine());

        int controlTimeInSec = min * 60 + sec;
        double reducedTime = (lengthMeters / 120 ) * 2.5;
        double timeOfMarin = (lengthMeters / 100 ) * secPer100Meter - reducedTime;

        if(timeOfMarin <= controlTimeInSec) {
            System.out.printf("Marin Bangiev won an Olympic quota!%nHis time is %.3f.", timeOfMarin);
        } else {
            System.out.printf("No, Marin failed! He was %.3f second slower.", timeOfMarin - controlTimeInSec);
        }
    }
}
