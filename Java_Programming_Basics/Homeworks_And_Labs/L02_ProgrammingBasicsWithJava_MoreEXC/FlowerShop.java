package Homeworks_And_Labs.L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class FlowerShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int magnolias = Integer.parseInt(scanner.nextLine());
        int hyacinths = Integer.parseInt(scanner.nextLine());
        int roses = Integer.parseInt(scanner.nextLine());
        int cactus = Integer.parseInt(scanner.nextLine());
        double giftPrice = Double.parseDouble(scanner.nextLine());

        double income = (magnolias * 3.25  + hyacinths * 4 + roses * 3.5 + cactus * 8) * 0.95;


        if(income > giftPrice) {
            double diff = income - giftPrice;
            System.out.printf("She is left with %.0f leva.", Math.floor(diff));
        } else if (income < giftPrice) {
            double diff = giftPrice - income;
            System.out.printf("She will have to borrow %.0f leva.", Math.ceil(diff));
        }
    }
}
