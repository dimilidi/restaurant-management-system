package L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class CircleAreaAndPerimeter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius = Double.parseDouble(scanner.nextLine());

        double circleArea = Math.PI * (radius * radius);
        double circlePerimeter =  radius * 2 * Math.PI;
        System.out.printf("%.2f %n", circleArea);
        System.out.printf("%.2f", circlePerimeter);
    }
}
