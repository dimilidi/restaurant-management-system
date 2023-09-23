package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class HalfSumElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int maxNum = Integer.MIN_VALUE;
        int sum = 0;

        for (int i = 0; i < count; i++){
            int num = Integer.parseInt(scanner.nextLine());
            maxNum = Math.max(maxNum, num);
            sum += num;
        }

        int sumWithoutMaxNum = Math.abs(maxNum - sum);
        int diff = Math.abs(maxNum - sumWithoutMaxNum);

        if(maxNum == (sum - maxNum)) {
            System.out.printf("Yes%nSum = %d", maxNum);
        } else {
            System.out.printf("No%nDiff = %d", diff);
        }
    }
}
