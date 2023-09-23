package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class Repainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double nylon = Double.parseDouble(scanner.nextLine());
        double paint = Double.parseDouble(scanner.nextLine()) ;
        double diluent = Double.parseDouble(scanner.nextLine());
        double hoursForPainting = Double.parseDouble(scanner.nextLine());
        double paintExtra = paint * 10 / 100 ;
        double paintTotal = paint + paintExtra;
        double nylonExtra = 2;
        double nylonTotal = nylon + nylonExtra;
        double moneyForBags = 0.4;
        double totalMaterialsPrice = (nylonTotal * 1.5) + (paintTotal * 14.5) + (diluent * 5)  + moneyForBags;
        double workPricePerHour = totalMaterialsPrice * 30 / 100;
        double totalPrice = totalMaterialsPrice + (workPricePerHour * hoursForPainting);
        System.out.println(totalPrice);
    }
}
