package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class BonusScore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int startPoints = Integer.parseInt(scanner.nextLine());
        double bonus = 0;

        if(startPoints <= 100) {
            bonus = 5;
        } else if (startPoints > 1000) {
            bonus = startPoints * 0.1;
        } else {
            bonus = startPoints * 0.2;
        }

        if (startPoints % 2 == 0) {
            bonus = bonus + 1;
        } else if (startPoints % 10 == 5) {
            bonus = bonus + 2;
        }

        System.out.println(bonus);
        System.out.println(startPoints + bonus);

    }
}
