package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_More_Exc;

import java.util.Scanner;

public class GamingStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double currentBalance = Double.parseDouble(scanner.nextLine());
        String input = scanner.nextLine();
        double price = 0.0;
        double totalSpent = 0.0;
        boolean isValid = false;

        while(!input.equals("Game Time")) {

            switch(input){
                case "OutFall 4":
                    price = 39.99;
                    isValid = true;
                    break;
                case "CS: OG":
                    price = 15.99;
                    isValid = true;
                    break;
                case "Zplinter Zell":
                    price = 19.99;
                    isValid = true;
                    break;
                case "Honored 2":
                    price = 59.99;
                    isValid = true;
                    break;
                case "RoverWatch":
                    price = 29.99;
                    isValid = true;
                    break;
                case "RoverWatch Origins Edition":
                    price = 39.99;
                    isValid = true;
                    break;
                default:
                    isValid= false;
            }

            if(currentBalance <= 0) {
                System.out.println(" Out of money!");
                break;
            }

            if(!isValid){
                System.out.println("Not Found");
            }else {
                if (currentBalance < price) {
                    System.out.println("Too Expensive");
                } else {
                    currentBalance = currentBalance - price;
                    totalSpent += price;
                    System.out.printf("Bought %s %n", input);
                }
            }
            input = scanner.nextLine();
        }
        System.out.printf("Total spent: $%.2f. Remaining: $%.2f", totalSpent, currentBalance );
    }
}
