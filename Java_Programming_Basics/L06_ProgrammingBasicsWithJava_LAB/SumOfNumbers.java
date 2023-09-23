package L06_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class SumOfNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int start = Integer.parseInt(scanner.nextLine());
        int end =  Integer.parseInt(scanner.nextLine());
        int magicNum = Integer.parseInt(scanner.nextLine());
        int counter = 0;
        boolean isFound = false;

        for (int i = start; i <= end; i++) {
            for (int j = start; j <= end ; j++) {
                if(isFound) break;
                counter++;
                if(i + j == magicNum) {
                    isFound = true;
                    System.out.printf("Combination N:%d (%d + %d = %d)", counter, i, j, magicNum);
                }
            }
        }
        if(!isFound) {
            System.out.printf("%d combinations - neither equals %d", counter, magicNum);
        }
    }
}
