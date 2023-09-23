package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_LAB;

import java.math.BigDecimal;
import java.util.Scanner;

public class ExactSumOfRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int input = Integer.parseInt(scanner.nextLine());
        BigDecimal sum = BigDecimal.valueOf(0);

        for (int i = 0; i < input; i++) {
            BigDecimal number = new BigDecimal(scanner.nextLine());
            sum = sum.add(number);
        }

        System.out.println(sum);

    }
}
