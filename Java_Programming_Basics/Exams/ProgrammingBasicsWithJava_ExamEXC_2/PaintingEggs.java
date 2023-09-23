package Exams.ProgrammingBasicsWithJava_ExamEXC_2;

import java.util.Scanner;

public class PaintingEggs {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String eggSize = scanner.nextLine();
        String eggColour = scanner.nextLine();
        int count = Integer.parseInt(scanner.nextLine());
        double price = 0.0;

        if(eggSize.equalsIgnoreCase("large")) {
            if(eggColour.equalsIgnoreCase("red")) {
                price = 16;
            } else if(eggColour.equalsIgnoreCase("green")) {
                price = 12;
            } else if(eggColour.equalsIgnoreCase("yellow")) {
                price = 9;
            }
        } else if (eggSize.equalsIgnoreCase("medium")) {
            if(eggColour.equalsIgnoreCase("red")) {
                price = 13;
            } else if(eggColour.equalsIgnoreCase("green")) {
                price = 9;
            } else if(eggColour.equalsIgnoreCase("yellow")) {
                price = 7;
            }
        } else if (eggSize.equalsIgnoreCase("small")) {
            if(eggColour.equalsIgnoreCase("red")) {
                price = 9;
            } else if(eggColour.equalsIgnoreCase("green")) {
                price = 8;
            } else if(eggColour.equalsIgnoreCase("yellow")) {
                price = 5;
            }
        }

        double totalPrice = (count * price) - 0.35 * (count * price);
        System.out.printf("%.2f leva.", totalPrice);
    }
}
