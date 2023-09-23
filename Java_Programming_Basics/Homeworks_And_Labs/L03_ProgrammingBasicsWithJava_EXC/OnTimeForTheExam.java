package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class OnTimeForTheExam {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hourExam = Integer.parseInt(scanner.nextLine());
        int minuteExam = Integer.parseInt(scanner.nextLine());
        int hourArrival = Integer.parseInt(scanner.nextLine());
        int minuteArrival = Integer.parseInt(scanner.nextLine());
        int totalMinutesExam = (hourExam * 60) + minuteExam;
        int totalMinutesArrival = (hourArrival * 60) + minuteArrival;
        boolean isLate = false;
        boolean  isEarly = false;
        boolean isOnTime = false;

        int timeDifferenceExamArrival = Math.abs(totalMinutesExam - totalMinutesArrival);
        int hourDifferenceExamArrival = timeDifferenceExamArrival / 60;
        int minutesDifferenceExamArrival =  timeDifferenceExamArrival % 60;

        if(totalMinutesExam >= totalMinutesArrival && timeDifferenceExamArrival <= 30 ){
            isOnTime = true;
        } else if (totalMinutesExam >= totalMinutesArrival && timeDifferenceExamArrival > 30 ) {
            isEarly = true;
        } else if(totalMinutesExam < totalMinutesArrival) {
            isLate = true;
        }

        if (isLate) {
            if (timeDifferenceExamArrival > 59) {
                if (minutesDifferenceExamArrival < 10) {
                    System.out.printf("Late %n%d:0%d  hours after the start", hourDifferenceExamArrival, minutesDifferenceExamArrival);
                } else {
                    System.out.printf("Late %n%d:%d  hours after the start", hourDifferenceExamArrival, minutesDifferenceExamArrival);
                }
            } else {
                System.out.printf("Late %n%d minutes after the start", timeDifferenceExamArrival);
            }
        } else if (isOnTime) {
            if(timeDifferenceExamArrival == 0){
                System.out.println("On time");
            } else {
                System.out.printf("On time %n%d minutes before the start", timeDifferenceExamArrival);
            }
        } else if (isEarly) {
            if (timeDifferenceExamArrival > 59) {
                if (minutesDifferenceExamArrival < 10) {
                    System.out.printf("Early %n%d:0%d  hours before the start", hourDifferenceExamArrival, minutesDifferenceExamArrival);
                } else {
                    System.out.printf("Early %n%d:%d  hours before the start", hourDifferenceExamArrival, minutesDifferenceExamArrival);
                }
            } else {
                System.out.printf("Early %n%d minutes before the start", timeDifferenceExamArrival);
            }
        }
    }
}
