package L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class OperationsBetweenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n1 = Integer.parseInt(scanner.nextLine());
        int n2 = Integer.parseInt(scanner.nextLine());
        String operator = scanner.nextLine();

        double result = 0.0;
        boolean isEven = false;

        switch (operator) {
            case "+":
                result = n1 + n2;
                isEven = result % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s%n", n1, operator, n2, result, isEven ? "even" : "odd");
                break;
            case "-":
                result = n1 - n2;
                isEven = result % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s%n", n1, operator, n2, result, isEven ? "even" : "odd");
                break;
            case "*":
                result = n1 * n2;
                isEven = result % 2 == 0;
                System.out.printf("%d %s %d = %.0f - %s%n", n1, operator, n2, result, isEven ? "even" : "odd");
                break;
            case "/":
                if (n2 == 0) {
                    System.out.printf("Cannot divide %d by zero%n", n1);
                } else {
                    result = (double) n1 / n2;
                    System.out.printf("%d %s %d = %.2f%n", n1, operator, n2, result);
                }
                break;
            case "%":
                if (n2 == 0) {
                    System.out.printf("Cannot divide %d by zero%n", n1);
                } else {
                    result = n1 % n2;
                    System.out.printf("%d %s %d = %.0f%n", n1, operator, n2, result);
                }
                break;
        }
    }
}
