package L01_ProgramingBasicsWithJava_LAB;

import java.util.Scanner;

public class RectangleArea {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int a = Integer.parseInt(scanner.nextLine());
        int b = Integer.parseInt(scanner.nextLine());
        int area = a * b;
        System.out.print(area);
    }
}
