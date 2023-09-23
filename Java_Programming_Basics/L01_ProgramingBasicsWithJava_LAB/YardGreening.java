package L01_ProgramingBasicsWithJava_LAB;

import java.util.Scanner;

public class YardGreening {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double yardArea = Double.parseDouble(scanner.nextLine());
        double totalPrice = yardArea * 7.61;
        double discount =  (0.18 * totalPrice);
        double priceWithDiscount = totalPrice - discount;
        System.out.printf("The final price is: %.2f lv. %nThe discount is: %.2f lv.", priceWithDiscount, discount);
    }
}
