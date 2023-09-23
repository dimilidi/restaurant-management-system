package ProgrammingBasicsWithJava_PreExam;

import java.util.Scanner;

public class UniquePINCodes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int upperBoundFirstDigit = Integer.parseInt(scanner.nextLine());
        int upperBoundSecondDigit = Integer.parseInt(scanner.nextLine());
        int upperBoundThirdDigit = Integer.parseInt(scanner.nextLine());

        for (int firstDigit = 2; firstDigit <= upperBoundFirstDigit; firstDigit += 2) {
            for (int secondDigit = 2; secondDigit <= upperBoundSecondDigit; secondDigit++) {
                if (isPrime(secondDigit)) {
                    for (int thirdDigit = 2; thirdDigit <= upperBoundThirdDigit; thirdDigit += 2) {
                        System.out.printf("%d. %d* %d:%n", firstDigit, secondDigit, thirdDigit);
                    }
                }
            }
        }
        System.out.println();
    }

    // Метод за проверка дали число е просто
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}
