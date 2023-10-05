package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.OpinionPoll;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countPeople = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < countPeople; i++) {
            String[] info = scanner.nextLine().split(" ");
            String name = info[0];
            int age = Integer.parseInt(info[1]);

            Person person = new Person(name, age);
            if(person.age > 30) {
                System.out.printf("%s - %d%n",name, age);
            }
        }
    }
}
