package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class Graduation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String studentName = scanner.nextLine();
        double grade = Double.parseDouble(scanner.nextLine());
        double finalGrade = 0.0;
        boolean isExcluded = false;
        int gradeCounter = 0;

        while(gradeCounter <= 12){
            gradeCounter++;
            finalGrade += grade;

            if(grade < 4.00) {
                isExcluded = true;
                break;
            }

            if (gradeCounter == 12) {
                break;
            }
            grade = Double.parseDouble(scanner.nextLine());
        }

        double avrgGrade = finalGrade / gradeCounter;


        if(isExcluded){
            System.out.printf("%s has been excluded at %d grade", studentName, gradeCounter);
        } else {
            System.out.printf("%s graduated. Average grade: %.2f", studentName, avrgGrade);
        }



    }
}
