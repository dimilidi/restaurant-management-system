package L02_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class LunchBreak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String filmName = scanner.nextLine();
        int filmDuration =  Integer.parseInt(scanner.nextLine());
        int breakDuration = Integer.parseInt(scanner.nextLine());

        double lunchTime = breakDuration / 8.0;
        double relaxTime = breakDuration / 4.0;

        double leftTimeForFilm = breakDuration -  (lunchTime + relaxTime);
        double differenceLeftTimeForFilmAndFilmDuration = Math.ceil( Math.abs(filmDuration -   leftTimeForFilm));

        if(leftTimeForFilm >= filmDuration) {
            System.out.printf("You have enough time to watch %s and left with %.0f minutes free time.", filmName, differenceLeftTimeForFilmAndFilmDuration );
        } else {
            System.out.printf("You don't have enough time to watch %s, you need %.0f more minutes.", filmName, differenceLeftTimeForFilmAndFilmDuration);
        }
    }
}
