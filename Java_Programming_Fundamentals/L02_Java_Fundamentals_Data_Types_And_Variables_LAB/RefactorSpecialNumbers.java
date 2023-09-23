package L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.util.Scanner;

public class RefactorSpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countNum = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= countNum; i++) {
            int num = i;
            int sum = 0;

            while (num > 0) {
                sum += num % 10;
                num = num / 10;
            }
           boolean isSpecial = (sum == 5) || (sum == 7) || (sum == 11);
            String isSpecialString = Boolean.toString(isSpecial);
            String isSpecialCapitalCase = isSpecialString.substring(0, 1).toUpperCase() + isSpecialString.substring(1).toLowerCase();
            System.out.printf("%d -> %s%n", i, isSpecialCapitalCase);
        }

    }
}
