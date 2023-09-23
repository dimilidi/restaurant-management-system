package L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class HousePainting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double x = Double.parseDouble(scanner.nextLine()); //height of house
        double y = Double.parseDouble(scanner.nextLine()); // width of house
        double h = Double.parseDouble(scanner.nextLine()); // height of triangle side roof

        double doorArea = 1.2 * 2;
        double windowArea = 1.5 * 1.5;

        double squareWallAreaWithDoor =( x * x) - doorArea;
        double squareWallAreaWithoutDoor = x * x;


        double rectangleWallArea = x * y - windowArea;

        double wallsArea = squareWallAreaWithoutDoor + squareWallAreaWithDoor + (2 * rectangleWallArea);

        double rectangleRoofArea = x * y;
        double triangleRoofArea = x * h / 2;

        double roofArea = (2 * rectangleRoofArea) + (2 * triangleRoofArea);

        double greenPaintLiter = wallsArea / 3.4;
        double redPaintLiter = roofArea / 4.3;

        System.out.printf("%.2f %n", greenPaintLiter);
        System.out.printf("%.2f", redPaintLiter);
    }
}
