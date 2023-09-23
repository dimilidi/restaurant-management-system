package Homeworks_And_Labs.L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class VowelsSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String text = scanner.nextLine();

        int result = 0;

        int value = 0;

        for(int i = 0; i <= text.length() - 1; i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case 'a':
                    value = 1;
                    break;
                case 'e' :
                    value = 2;
                    break;
                case 'i':
                    value = 3;
                    break;
                case 'o':
                    value = 4;
                    break;
                case 'u' :
                    value = 5;
                    break;
                default:
                    value = 0;
            }
            result += value;
        }
        System.out.print(result);
    }
}
