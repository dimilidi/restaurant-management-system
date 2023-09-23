package L02_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class ExcellentResult {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int schoolGrade = Integer.parseInt(scanner.nextLine());
        if(schoolGrade >= 5) {
            System.out.println("Excellent!");
        }
    }
}
