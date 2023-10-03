package Homeworks_And_Labs.L06_Java_Fundamentals_Mid_Exam_Preparation_EXC;

import java.util.Scanner;

public class ComputerStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        double price = 0.0;
        double taxes = price * 0.2;
        double priceWithoutTaxes = 0.0;

        while (!input.equalsIgnoreCase("special") || !input.equalsIgnoreCase("regular")) {
            if(input.equalsIgnoreCase("special") || input.equalsIgnoreCase("regular")){
                break;
            }

            price = Double.parseDouble(input);

            if(price < 0) {
                System.out.println("Invalid price!");
            } else {
                priceWithoutTaxes += price;
                taxes += price * 0.2;
            }
            input = scanner.nextLine();
        }

        double totalPrice = 0.0;
        if(input.equalsIgnoreCase("regular")) {
             totalPrice = priceWithoutTaxes + taxes;
        } else if(input.equalsIgnoreCase("special")) {
             totalPrice = (priceWithoutTaxes + taxes) -  (priceWithoutTaxes + taxes) * 0.1;
        }

        if(totalPrice == 0.0){
            System.out.println("Invalid order!");
        } else {
            System.out.printf("Congratulations you've just bought a new computer!%nPrice without taxes: %.2f$%nTaxes: %.2f$%n ----------- %nTotal price: %.2f$", priceWithoutTaxes, taxes, totalPrice);
        }
    }
}
