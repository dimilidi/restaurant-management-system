package L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class OddEvenSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int sumOdd = 0;
        int sumEven = 0;

        for(int i = 0; i < count; i++) {

            if(i % 2 == 0) {
                int num = Integer.parseInt(scanner.nextLine());
                sumEven += num;
            } else {
                int num = Integer.parseInt(scanner.nextLine());
                sumOdd += num;
            }
        }

        if(sumEven == sumOdd) {
            System.out.printf("Yes%nSum = %d",sumEven);
        } else {
            System.out.printf("No%nDiff = %d",Math.abs(sumEven - sumOdd));
        }
    }
}
