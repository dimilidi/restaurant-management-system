package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class NumberInRange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Math.abs(Integer.parseInt(scanner.nextLine()));
        if(num <= 100 && num > 0){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
