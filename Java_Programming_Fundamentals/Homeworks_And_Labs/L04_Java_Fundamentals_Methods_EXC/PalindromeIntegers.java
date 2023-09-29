package Homeworks_And_Labs.L04_Java_Fundamentals_Methods_EXC;

import java.util.Scanner;

public class PalindromeIntegers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("end")) {
            int number = Integer.parseInt(input);
            System.out.println(checkIfPalindrome(number));
            input = scanner.nextLine();
        }

    }


    public static boolean checkIfPalindrome(int number) {
        String stringOfNums = String.valueOf(number);
        String[] numberToStringArr = stringOfNums.split("");
        String reversedStr = "";
        for (int i = numberToStringArr.length - 1; i >= 0; i--) {
            reversedStr += numberToStringArr[i];
        }

        return reversedStr.equals(stringOfNums);
    }
}
