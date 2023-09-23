package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class Sequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int input_num = Integer.parseInt(scanner.nextLine());
        int num = 1;

        while(input_num >= num) {
            System.out.println(num);
            num = (num * 2 ) + 1;
        }
    }
}
