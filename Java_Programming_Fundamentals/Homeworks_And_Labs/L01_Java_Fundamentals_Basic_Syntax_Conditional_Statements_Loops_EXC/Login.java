package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_EXC;

import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String userName = scanner.nextLine();
        String password = scanner.nextLine();

        String reversedUserName = "";
        int wrongPasswordCount = 0;
        String output = "";


        for (int i = userName.length() - 1; i >= 0 ; i--) {
            char letter = userName.charAt(i);
            reversedUserName += letter;
        }

        while(!password.equals(reversedUserName)) {
            if(password.equals(reversedUserName)) {
                output = "User" + userName + "logged in.";
                break;
            } else {
                if(wrongPasswordCount == 3) {
                    System.out.printf("User %s blocked!",userName);
                    return;
                }
                wrongPasswordCount++;
                System.out.println("Incorrect password. Try again.");
            }
            password = scanner.nextLine();

        }
        System.out.printf("User %s logged in.", userName);

    }
}
