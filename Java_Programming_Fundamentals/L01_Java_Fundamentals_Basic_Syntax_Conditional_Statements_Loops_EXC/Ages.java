package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class Ages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int age = Integer.parseInt(scanner.nextLine());
        String result = "";


        if(0 <= age && age <= 2) {
            result = "baby";
        } else if(3 <= age && age <= 13) {
            result = "child";
        } else if (14 <= age && age <= 19) {
            result = "teenager";
        } else if (20 <= age & age <= 65) {
            result = "adult";
        } else {
            result = "elder";
        }

        System.out.println(result);

    }
}
