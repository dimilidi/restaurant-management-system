package L02_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class AreaOrFigures {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            String shape = scanner.nextLine();

            switch(shape) {
                case "square":
                    double side = Double.parseDouble(scanner.nextLine());
                    double squareArea = side * side;
                    System.out.printf("%.3f", squareArea);
                    break;

                case "rectangle":
                    double width = Double.parseDouble(scanner.nextLine());
                    double height = Double.parseDouble(scanner.nextLine());
                    double rectangleArea = width * height;
                    System.out.printf("%.3f", rectangleArea);
                    break;

                case "circle":
                    double radius = Double.parseDouble(scanner.nextLine());
                    double circleArea = Math.PI * radius * radius;
                    System.out.printf("%.3f", circleArea);
                    break;

                case "triangle":
                    double triangleSide = Double.parseDouble(scanner.nextLine());
                    double triangleHeight = Double.parseDouble(scanner.nextLine());
                    double triangleArea = 0.5 * triangleSide * triangleHeight;
                    System.out.printf("%.3f", triangleArea);
                    break;

                default:
                    System.out.println("Invalid shape.");
            }
        }
}
