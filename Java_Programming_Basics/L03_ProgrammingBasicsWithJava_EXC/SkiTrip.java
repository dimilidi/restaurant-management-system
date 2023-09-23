package L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class SkiTrip {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int daysStay = Integer.parseInt(scanner.nextLine()) -1;
        String accommodationType = scanner.nextLine();
        String review = scanner.nextLine();
        double price = 0.00;

        switch(accommodationType) {
            case "room for one person":
                price = daysStay * 18.00;
                break;
            case "apartment":
                price = daysStay * 25.00;
                if(daysStay > 15) {
                    price *= 0.5;
                } else if (daysStay < 15 && daysStay > 10 ) {
                    price *= 0.65;
                } else {
                    price *= 0.7;
                }
                break;
            case "president apartment":
                price = daysStay * 35.00;
                if(daysStay > 15) {
                    price *= 0.8;
                } else if (daysStay < 15 && daysStay > 10 ) {
                    price *= 0.85;
                } else {
                    price *= 0.9;
                }
                break;
        }

        double priceAfterReview = 0.00;

        if(review.equalsIgnoreCase("positive")) {
            priceAfterReview = price  * 1.25;
        } else if (review.equalsIgnoreCase("negative")) {
            priceAfterReview = price * 0.9;
        }

        System.out.printf("%.2f",priceAfterReview);
    }
}
