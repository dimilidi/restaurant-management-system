package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeList {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while(!command.equalsIgnoreCase("end")) {
            String[] commandParts = command.split(" ");

            for (int i = 0; i < numbers.size(); i++) {
                if(commandParts[0].contains("Delete")){
                    numbers.removeAll(Arrays.asList(Integer.parseInt(commandParts[1])));
                } else if (commandParts[0].contains("Insert")) {
                    numbers.add(Integer.parseInt(commandParts[2]), Integer.parseInt(commandParts[1]));
                }
                break;
            }

            command = scanner.nextLine();
        }

        for(int number : numbers){
            System.out.print(number + " ");
        }

    }
}
