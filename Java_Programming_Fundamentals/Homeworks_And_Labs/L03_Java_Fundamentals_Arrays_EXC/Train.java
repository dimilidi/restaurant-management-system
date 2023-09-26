package Homeworks_And_Labs.L03_Java_Fundamentals_Arrays_EXC;

import java.util.Scanner;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int wagons = Integer.parseInt(scanner.nextLine());
        int[] peopleArr = new int[wagons];
        int totalPeople = 0;


        for (int i = 0; i < wagons ; i++) {
            int people = Integer.parseInt(scanner.nextLine());
            peopleArr[i] += people;
            System.out.print(peopleArr[i] + " ");
            totalPeople += people;
        }

        System.out.println();
        System.out.println(totalPeople);

    }
}
