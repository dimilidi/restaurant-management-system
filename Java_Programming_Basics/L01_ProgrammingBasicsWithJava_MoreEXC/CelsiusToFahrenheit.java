package L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class CelsiusToFahrenheit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double degreesInCelsius = Double.parseDouble(scanner.nextLine());
        double degreesInFahrenheit = degreesInCelsius *  1.8 + 32;
        System.out.printf("%.2f", degreesInFahrenheit);
    }
}
