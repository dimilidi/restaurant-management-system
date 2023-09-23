package L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class CenturiesToMinutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int centuries = Integer.parseInt(scanner.nextLine());

        double years = centuries * 100.0;
        double days = years * 365.2422;
        double hours = days * 24.0;
        double minutes = hours * 60.0;

        System.out.printf("%d centuries = %.0f years = %.0f days = %.0f hours = %.0f minutes", centuries, years, days, hours, minutes);


    }
}
