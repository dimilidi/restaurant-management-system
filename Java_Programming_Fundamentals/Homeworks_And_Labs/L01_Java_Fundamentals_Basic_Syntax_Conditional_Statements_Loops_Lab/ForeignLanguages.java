package Homeworks_And_Labs.L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class ForeignLanguages {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String country = scanner.nextLine();
        String lang = "";

        switch (country) {
            case "USA":
            case "England":
                lang = "English";
                break;
            case "Spain":
            case "Argentina":
            case "Mexico":
                lang = "Spanish";
                break;
            default:
                System.out.println("unknown");
        }
        System.out.println(lang);
    }
}
