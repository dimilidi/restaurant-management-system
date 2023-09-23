package L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class VacationBooksList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int pagesInBook = Integer.parseInt(scanner.nextLine());
        int pagesPerHour = Integer.parseInt(scanner.nextLine());
        int dayLimit = Integer.parseInt((scanner.nextLine()));
        int hoursPerDay = (pagesInBook / pagesPerHour) / dayLimit;
        System.out.println(hoursPerDay);

    }
}
