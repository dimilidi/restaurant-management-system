package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ListOperations {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("End")) {
            String[] commandParts = input.split("\\s+");

            if (input.contains("Add")) {
                numbers.add(Integer.parseInt(commandParts[1]));
            } else if (input.contains("Insert")) {
                int index = Integer.parseInt(commandParts[2]);
                if (index >= 0 && index < numbers.size()) {
                    numbers.add(index, Integer.parseInt(commandParts[1]));
                } else {
                    System.out.println("Invalid index");
                }
            } else if (input.contains("Remove")) {
                int index = Integer.parseInt(commandParts[1]);
                if (index >= 0 && index < numbers.size()) {
                    numbers.remove(index);
                } else {
                    System.out.println("Invalid index");
                }
            } else if (input.contains("Shift")) {

                for (int i = 0; i < Integer.parseInt(commandParts[2]); i++) {
                    if (input.contains("left")) {
                        numbers.add(numbers.get(0));
                        numbers.remove(0);
                    } else if (input.contains("right")) {
                        int lastElement = numbers.get(numbers.size() - 1);
                        numbers.add(0, lastElement);
                        numbers.remove(numbers.size() - 1);
                    }
                }

            }
            input = scanner.nextLine();

        }
        System.out.println(numbers.toString()
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
        );
    }
}
