package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Moving {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int width = Integer.parseInt(scanner.nextLine());
        int length = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());

        int space = width * length * height;
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("Done")) {
            int boxes = Integer.parseInt(input);
            space -= boxes;

            if(space <= 0) {
                System.out.printf("No more free space! You need %d Cubic meters more.", Math.abs(space));
                break;
            }

            input = scanner.nextLine();
        }

        if((input.equalsIgnoreCase("Done"))) {
            System.out.printf("%d Cubic meters left.", Math.abs(space));
        }
        
    }
}
