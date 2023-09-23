package L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class SumPrimeNonePrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int sumPrime = 0;
        int sumNonPrime = 0;

        while(!input.equalsIgnoreCase("stop")) {
            int num = Integer.parseInt(input);
            boolean isItPrime = true;

            if(num < 0) {
                isItPrime = false;
                System.out.printf("Number is negative.%n");
                input = scanner.nextLine();
                continue;
            }

            if(num == 1) {
                isItPrime = false;
            } else {
                for (int i = 2; i <= Math.sqrt(num); i++) {
                    if(num  % i == 0) {
                        isItPrime = false;
                    }
                }
            }

            if (isItPrime) {
                sumPrime += num;
            } else {
                sumNonPrime += num;
            }

            input = scanner.nextLine();
        }
        System.out.printf("Sum of all prime numbers is: %d%n", sumPrime);
        System.out.printf("Sum of all non prime numbers is: %d", sumNonPrime);
    }
}
