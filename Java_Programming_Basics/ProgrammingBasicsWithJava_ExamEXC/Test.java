package ProgrammingBasicsWithJava_ExamEXC;

import java.util.Scanner;


public class Test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int goal = Integer.parseInt(scanner.nextLine());

        int currentGoal = goal - 30;
        int countFailedJump = 0;
        int countJump = 0;
        boolean isGoal = false;


        while (true) {
            countJump++;
            int jump = Integer.parseInt(scanner.nextLine());

            if (jump <= currentGoal) {
                countFailedJump++;
            } else {
                currentGoal += 5;
                countFailedJump = 0;
            }

            if (currentGoal > goal) {
                isGoal = true;
                break;
            }

            if (countFailedJump == 3) {
                break;
            }

        }

        if (isGoal) {
            System.out.printf("Tihomir succeeded, he jumped over %dcm after %d jumps.", goal, countJump);
        } else {
            System.out.printf("Tihomir failed at %dcm after %d jumps.", currentGoal, countJump);
        }
    }
}


