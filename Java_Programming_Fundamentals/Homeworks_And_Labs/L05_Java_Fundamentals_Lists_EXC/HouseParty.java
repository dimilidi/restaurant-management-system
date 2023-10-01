package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HouseParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countCommands = Integer.parseInt(scanner.nextLine());
        List<String> peopleList = new ArrayList<>();

        for (int i = 0; i < countCommands; i++) {
            String command = scanner.nextLine();
            String[] commandArr = command.split(" ");
            if(command.contains("not")) {
                if(peopleList.contains(commandArr[0])){
                    peopleList.remove(commandArr[0]);
                } else {
                    System.out.printf("%s is not in the list!%n", commandArr[0]);
                }
            } else {
                if(!peopleList.contains(commandArr[0])){
                    peopleList.add(commandArr[0]);
                } else {
                    System.out.printf("%s is already in the list!%n", commandArr[0]);
                }
            }
        }

       for(String person : peopleList) {
           System.out.println(person);
       }
    }
}
