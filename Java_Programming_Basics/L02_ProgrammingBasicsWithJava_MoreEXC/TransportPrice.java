package L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class TransportPrice {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int distance = Integer.parseInt(scanner.nextLine());
        String timePeriod = scanner.nextLine();
        double price = 0.00;

        if(distance < 20) {
            if(timePeriod.equals("day")) {
                price = 0.70 + (0.79 * distance);
            } else {
                price = 0.70 + (0.90 * distance);
            }
        } else if (distance >= 20 && distance < 100) {
            price = 0.09 * distance;
        } else if(distance >= 100) {
            price = 0.06 * distance;
        }

        System.out.printf("%.2f",price);
    }
}
