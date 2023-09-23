package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class TrekkingMania {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());

        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        int p4 = 0;
        int p5 = 0;

        for (int i = 0; i < count; i++) {
            int number = Integer.parseInt(scanner.nextLine());

            if (number < 6) {
                p1 += number;
            } else if (number < 13) {
                p2 += number;
            } else if (number < 26) {
                p3 += number;
            } else if (number < 41) {
                p4 += number;
            } else {
                p5 += number;
            }
        }

        int totalPerson = p1 + p2+ p3 + p4 + p5;

        double percentP1 = p1 * 100.00 / totalPerson;
        double percentP2 = p2 * 100.00 / totalPerson;
        double percentP3 = p3 * 100.00 / totalPerson;
        double percentP4 = p4 * 100.00 / totalPerson;
        double percentP5 = p5 * 100.00 / totalPerson;

        System.out.printf("%.2f%%%n", percentP1);
        System.out.printf("%.2f%%%n", percentP2);
        System.out.printf("%.2f%%%n", percentP3);
        System.out.printf("%.2f%%%n", percentP4);
        System.out.printf("%.2f%%%n", percentP5);
    }
}
