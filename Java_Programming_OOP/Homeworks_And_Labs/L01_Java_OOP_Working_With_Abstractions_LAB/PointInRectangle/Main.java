package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.PointInRectangle;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] coordinates = readArray(scanner);

        Rectangle rect = new Rectangle(coordinates[0], coordinates[1], coordinates[2], coordinates[3]);

        int pointsCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < pointsCount; i++) {
            int[] pointCoordinates = readArray(scanner);

            Point p = new Point(pointCoordinates[0], pointCoordinates[1]);

            System.out.println(rect.contains(p));
        }
    }
    private static int[] readArray(Scanner scanner) {
        return Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
}
