package Homeworks_And_Labs.L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class DepositCalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double deposit = Double.parseDouble(scanner.nextLine());
        int timeLimit = Integer.parseInt(scanner.nextLine());
        double interestRate = Double.parseDouble(scanner.nextLine()) / 100;
        double sumWithInterestRate = deposit + timeLimit * ((deposit * interestRate) / 12);
        System.out.println(sumWithInterestRate);
    }
}
