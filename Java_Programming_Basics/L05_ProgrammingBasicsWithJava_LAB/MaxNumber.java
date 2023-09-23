package L05_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class MaxNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int max = Integer.MIN_VALUE;

        while(!(input.equals("Stop"))) {
            input = scanner.nextLine();
            if(input.equalsIgnoreCase("Stop")) {
                break;
            }
            int num = Integer.parseInt(input);
            max = Math.max(max, num);
        }
        System.out.println(max);
    }
}
