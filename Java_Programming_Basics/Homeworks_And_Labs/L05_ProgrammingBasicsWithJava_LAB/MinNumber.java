package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class MinNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int min = Integer.MAX_VALUE;

        while(!(input.equals("Stop"))) {
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("Stop")) {
                break;
            }
            int num = Integer.parseInt(input);
            min = Math.min(min, num);
        }
        System.out.println(min);
    }

}
