package L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Coins {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        double changeAmount = Double.parseDouble(scanner.nextLine());

        int coinsCount = 0;
        int changeAmountInCents = (int) (changeAmount * 100);

        while (changeAmountInCents > 0) {
            if (changeAmountInCents >= 200) {
                coinsCount++;
                changeAmountInCents -= 200;
            } else if (changeAmountInCents >= 100) {
                coinsCount++;
                changeAmountInCents -= 100;
            } else if (changeAmountInCents >= 50) {
                coinsCount++;
                changeAmountInCents -= 50;
            } else if (changeAmountInCents >= 20) {
                coinsCount++;
                changeAmountInCents -= 20;
            } else if (changeAmountInCents >= 10) {
                coinsCount++;
                changeAmountInCents -= 10;
            } else if (changeAmountInCents >= 5) {
                coinsCount++;
                changeAmountInCents -= 5;
            } else if (changeAmountInCents >= 2) {
                coinsCount++;
                changeAmountInCents -= 2;
            } else if (changeAmountInCents >= 1) {
                coinsCount++;
                changeAmountInCents -= 1;
            }
        }

        System.out.println(coinsCount);
    }





}
