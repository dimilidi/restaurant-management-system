package Homeworks_And_Labs.L06_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class Building {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int floors = Integer.parseInt(scanner.nextLine());
        int rooms = Integer.parseInt(scanner.nextLine());

        for (int i = floors; i >= 1; i--) {
            int floorN = i;
            int roomN = 0;
            System.out.printf("%n");

            for (int j = 0; j < rooms; j++) {
                roomN = j;
                if(floorN == floors) {
                    System.out.printf("L%d%d ", floorN, roomN );
                } else if(floorN % 2 == 0) {
                    System.out.printf("O%d%d ", floorN, roomN );
                } else {
                    System.out.printf("A%d%d ", floorN, roomN );
                }
            }
        }
    }
}
