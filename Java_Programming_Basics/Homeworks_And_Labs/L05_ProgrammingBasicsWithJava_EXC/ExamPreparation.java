package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class ExamPreparation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int badGradeCountPermitted = Integer.parseInt(scanner.nextLine());
        String task = scanner.nextLine();
        int grade = Integer.parseInt(scanner.nextLine());;

        boolean isEnough = false;
        boolean isBreak = false;
        int counterProblems = 0;
        int counterBadGrades = 0;
        double totalScore = 0.0;
        String lastTask = "";

        while(!(isBreak && isEnough)) {
            counterProblems++;
            totalScore += grade;

            if(grade <= 4){
                counterBadGrades++;
            }

            if(counterBadGrades >= badGradeCountPermitted) {
                isBreak = true;
                break;
            }

            lastTask = task;
            task = scanner.nextLine();

            if(task.equals("Enough")){
                isEnough = true;
                break;
            }

            grade = Integer.parseInt(scanner.nextLine());
        }

        double avrgScore = totalScore / counterProblems;

        if(isEnough) {
            System.out.printf("Average score: %.2f%nNumber of problems: %d%nLast problem: %s", avrgScore, counterProblems, lastTask);
        } else if (isBreak) {
            System.out.printf("You need a break, %d poor grades.", counterBadGrades);
        }
    }
}
