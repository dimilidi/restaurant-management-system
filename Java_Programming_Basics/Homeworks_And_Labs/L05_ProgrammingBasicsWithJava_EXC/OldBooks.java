package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class OldBooks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String favourite_book = scanner.nextLine();
        String book_checked = "";
        int count = 0;

        while(!book_checked.equals(favourite_book)){

            book_checked = scanner.nextLine();
            if(book_checked.equals("No More Books")) {
                System.out.printf("The book you search is not here!%nYou checked %d books.", count);
                break;
            }else if(book_checked.equals(favourite_book)){
                System.out.printf("You checked %d books and found it.", count);
                break;
            }
            count++;

        }
    }
}
