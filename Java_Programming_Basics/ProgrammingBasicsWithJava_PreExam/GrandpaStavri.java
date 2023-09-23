package ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class GrandpaStavri {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int days = Integer.parseInt(scanner.nextLine());
        double rakiaTotal = 0.0;
        double degreesTotal = 0.0;


        int dayNo = 0;

        while(dayNo < days) {
            dayNo++;
            double rakiaCurr = Double.parseDouble(scanner.nextLine());
            double degrees = Double.parseDouble(scanner.nextLine());
            rakiaTotal += rakiaCurr;
            degreesTotal += (rakiaCurr * degrees);
        }

       double avgDegrees = degreesTotal / rakiaTotal;

        System.out.printf("Liter: %.2f%n", rakiaTotal);
        System.out.printf("Degrees: %.2f%n",  avgDegrees);

        if(avgDegrees < 38) {
            System.out.println("Not good, you should baking!" );
        } else if (avgDegrees >= 38 && avgDegrees < 42) {
            System.out.println("Super!");
        } else {
            System.out.println("Dilution with distilled water!");
        }
    }
}
