package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.OrderBvAge;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        List<Person> personList = new ArrayList<>();

        while(!command.equalsIgnoreCase("end")){
            String[] commandParts = command.split(" ");
            String name = commandParts[0];
            String id = commandParts[1];
            int age = Integer.parseInt(commandParts[2]);

            Person person = new Person(name, id, age);
            personList.add(person);
            command = scanner.nextLine();
        }

        personList.sort(Comparator.comparingInt(Person::getAge));


        for(Person person : personList){
            System.out.println(person.toString());
        }
    }
}
