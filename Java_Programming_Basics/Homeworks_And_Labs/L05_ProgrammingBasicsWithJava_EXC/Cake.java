package Homeworks_And_Labs.L05_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Cake {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width = Integer.parseInt(scanner.nextLine());
        int height = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();
        int pieces = width * height;

        while(!input.equalsIgnoreCase("Stop")) {
            int eatenPieces = Integer.parseInt(input);
            pieces -= eatenPieces;

            if(pieces <= 0){
                System.out.printf("No more cake left! You need %d pieces more.", Math.abs(pieces));
                break;
            }

            input = scanner.nextLine();
        }

        if(pieces > 0){
            System.out.printf("%d pieces are left.", pieces);

        }

    }
}
