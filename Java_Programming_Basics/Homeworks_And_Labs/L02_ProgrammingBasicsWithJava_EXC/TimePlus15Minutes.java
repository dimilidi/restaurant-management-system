package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class TimePlus15Minutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int hours = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine()) + 15;

        int totalMinutes = (hours * 60) + minutes;

        int newHours = totalMinutes / 60;
        int newMinutes = totalMinutes % 60;

        if(newHours == 24) {
            newHours = 0;
        }

        if(newMinutes < 10) {
            System.out.printf("%d:0%d", newHours, newMinutes);
        } else {
            System.out.printf("%d:%d", newHours, newMinutes);
        }
    }
}
