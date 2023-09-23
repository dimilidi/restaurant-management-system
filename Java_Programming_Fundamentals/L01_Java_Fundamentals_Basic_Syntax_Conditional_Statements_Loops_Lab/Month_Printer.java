package L01_Java_Fundamentals_Basic_Syntax_Conditional_Statements_Loops_Lab;

import java.util.Scanner;

public class Month_Printer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        String result = "";

         switch (number) {
            case 1:
                result= "January";
                break;
             case 2:
                 result= "February";
                 break;
             case 3:
                 result= "March";
                 break;
             case 4:
                 result= "April";
                 break;
             case 5:
                 result= "Mai";
                 break;
             case 6:
                 result= "June";
                 break;
             case 7:
                 result= "July";
                 break;
             case 8:
                 result= "August";
                 break;
             case 9:
                 result= "September";
                 break;
             case 10:
                 result= "Oktober";
                 break;
             case 11:
                 result= "November";
                 break;
             case 12:
                 result= "December";
                 break;
             default:
                 result = "Error!";
         }
        System.out.println(result);
   }
}


