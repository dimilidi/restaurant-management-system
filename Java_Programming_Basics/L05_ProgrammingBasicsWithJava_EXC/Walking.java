package L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Walking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int stepsToHome = 0;
        int stepsTotal = 0;
        int goal = 10000;

        while (!input.equalsIgnoreCase("Going Home")) {
            int steps = Integer.parseInt(input);
            stepsTotal += steps;

            if (stepsTotal >= goal) {
                break;
            }

            input = scanner.nextLine();
        }

        if (input.equalsIgnoreCase("Going Home")) {
            stepsToHome = Integer.parseInt(scanner.nextLine());
            stepsTotal += stepsToHome;
        }

        int stepsDifference = Math.abs(stepsTotal - goal);

        if (stepsTotal >= goal) {
            System.out.printf("Goal reached! Good job!%n%d steps over the goal!", stepsDifference);
        } else {
            System.out.printf("%d more steps to reach goal.", stepsDifference);
        }
    }
}
