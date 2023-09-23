package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class Back_In_30_Minutes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int hours = Integer.parseInt(scanner.nextLine());
        int minutes = Integer.parseInt(scanner.nextLine());
        int resultTime = (hours * 60) + minutes + 30;
        int resultHours = resultTime / 60;
        int resultMinutes = resultTime % 60;

        if(resultHours == 24) {
            resultHours = 0;
        }

        System.out.printf("%d:%02d", resultHours, resultMinutes);
    }
}
