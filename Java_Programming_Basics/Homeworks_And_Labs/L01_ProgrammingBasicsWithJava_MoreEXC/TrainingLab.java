package Homeworks_And_Labs.L01_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class TrainingLab {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double width = Double.parseDouble(scanner.nextLine()) ;
        double height = Double.parseDouble(scanner.nextLine()) ;

        double desksPerRow = ((height * 100) - 100) / 70;
        double rows =  width * 100 / 120;
        int totalDesks =  (int) desksPerRow *  (int) rows - 3;

        System.out.printf("%d", totalDesks);
    }
}

