package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class PassedOrFailed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double grade = Double.parseDouble(scanner.nextLine());
        String result = "";

        if(grade >= 3.00){
            result = "Passed!";
        } else {
            result = "Failed!";
        }

        System.out.println(result);
    }
}
