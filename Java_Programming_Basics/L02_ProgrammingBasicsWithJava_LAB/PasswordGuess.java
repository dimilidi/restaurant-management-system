package L02_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class PasswordGuess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String psw = scanner.nextLine();

        if (psw.equals("s3cr3t!P@ssw0rd")){
            System.out.println("Welcome");
        } else {
            System.out.println("Wrong Password!");
        }
    }
}
