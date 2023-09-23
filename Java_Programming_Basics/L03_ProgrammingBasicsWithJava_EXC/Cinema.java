package L03_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String typeProjection = scanner.nextLine().toLowerCase();
        int rows = Integer.parseInt(scanner.nextLine());
        int columns = Integer.parseInt(scanner.nextLine());
        double totalIncome = 0.00;

        switch(typeProjection) {
            case "premiere":
                totalIncome = rows * columns * 12.00;
                break;
            case "normal":
                totalIncome = rows * columns * 7.50;
                break;
            case "discount":
                totalIncome = rows * columns * 5.00;
                break;
        }

        System.out.printf("%.2f leva", totalIncome);
    }
}
