package L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class SuppliesForSchool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double penPacks = Double.parseDouble(scanner.nextLine());
        double markerPacks = Double.parseDouble(scanner.nextLine());
        double boardCleaner =Double.parseDouble((scanner.nextLine()));
        double discount = Double.parseDouble((scanner.nextLine())) / 100;
        double totalPrice = (penPacks * 5.80) + (markerPacks * 7.20) + (boardCleaner * 1.20);
        double totalWithDiscount = totalPrice - (totalPrice * discount);
        System.out.println(totalWithDiscount);
    }
}
