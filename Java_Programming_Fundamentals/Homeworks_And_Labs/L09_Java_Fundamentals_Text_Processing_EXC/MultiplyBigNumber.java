package Homeworks_And_Labs.L09_Java_Fundamentals_Text_Processing_EXC;

import java.util.Scanner;

public class MultiplyBigNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String first = scanner.nextLine();
        int second = Integer.parseInt(scanner.nextLine());

        StringBuilder result = new StringBuilder();
        int carry = 0;

        // Iterate through the digits of the first number from right to left
        for (int i = first.length() - 1; i >= 0; i--) {
            int digit = first.charAt(i) - '0';
            int product = digit * second + carry;
            result.insert(0, product % 10);  // Insert the digit at the beginning
            carry = product / 10;
        }

        // Handle any remaining carry
        if (carry != 0) {
            result.insert(0, carry);
        }

        // Remove leading zeros
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
        }

        System.out.println(result);
    }


}
