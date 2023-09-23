package Exams.ProgrammingBasicsWithJava_ExamEXC;

import java.util.Scanner;

public class OscarCeremony {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rentFee = Integer.parseInt(scanner.nextLine());
        double statuesPrice = rentFee - (rentFee * 0.3);
        double cateringPrice = statuesPrice - (statuesPrice * 0.15);
        double soundPrice = cateringPrice / 2;

        double totalSum = rentFee + statuesPrice + cateringPrice + soundPrice;
        System.out.printf("%.2f", totalSum);
    }

}
