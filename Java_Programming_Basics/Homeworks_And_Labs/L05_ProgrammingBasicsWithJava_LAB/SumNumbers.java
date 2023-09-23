package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num_initial = Integer.parseInt(scanner.nextLine());
        int sum = 0;

        while(num_initial > sum){
            int num_current = Integer.parseInt(scanner.nextLine());
            sum += num_current;

        }
        System.out.println(sum);
    }

}
