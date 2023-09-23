package Homeworks_And_Labs.L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class SpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = Integer.parseInt(scanner.nextLine());
        String specialNum = "";

        for (int i = 1111; i <= 9999 ; i++) {
            int specialDigitCount = 0;
            String current = "" + i;

            for (int j = 0; j < 4; j++) {
                int digit = Integer.parseInt("" + current.charAt(j));
                if(digit == 0) continue;
                if(num % digit == 0) {
                    specialDigitCount++;
                }
            }

            if(specialDigitCount == 4) {
                specialNum += current + " ";
            }
        }
        System.out.println(specialNum);
    }
}
