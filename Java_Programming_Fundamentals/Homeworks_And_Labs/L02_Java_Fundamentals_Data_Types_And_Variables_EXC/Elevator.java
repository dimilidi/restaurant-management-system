package Homeworks_And_Labs.L02_Java_Fundamentals_Data_Types_And_Variables_EXC;

import java.util.Scanner;

public class Elevator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberPeople = Integer.parseInt(scanner.nextLine());
        int elevatorCapacity = Integer.parseInt(scanner.nextLine());
        int courses = 0;

        if (numberPeople < elevatorCapacity) {
            courses = 1;
        } else if (numberPeople % elevatorCapacity == 0) {
            courses = numberPeople / elevatorCapacity;
        } else {
            courses = (numberPeople / elevatorCapacity) + 1;
        }

        System.out.println(courses);
    }

}
