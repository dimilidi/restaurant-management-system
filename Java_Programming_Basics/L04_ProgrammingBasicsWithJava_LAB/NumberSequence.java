package L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class NumberSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        int maxNum = Integer.MIN_VALUE;
        int minNum = Integer.MAX_VALUE;

        for (int i = 0; i < count; i++) {
           int num = Integer.parseInt(scanner.nextLine());
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
        }
        System.out.println("Max number: "+ maxNum);
        System.out.println("Min number: "+ minNum);
    }
}
