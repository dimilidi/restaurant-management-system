package L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class EqualSumsEvenOddPosition {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());


        for (int i = n1; i <= n2; i++) {
            int oddSum = 0;
            int evenSum = 0;
            String current = "" + i;

            for (int j = 0; j < current.length(); j++) {
                int currentDigit = Integer.parseInt("" + current.charAt(j));
                if(j % 2 == 0){
                    evenSum += currentDigit;
                } else {
                    oddSum += currentDigit;
                }
            }

            if(evenSum == oddSum) {
                System.out.print(current + " ");
            }
        }
    }
}
