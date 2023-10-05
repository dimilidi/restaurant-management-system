package Homeworks_And_Labs.L06_Java_Fundamentals_Mid_Exam_Preparation_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Inventory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> items = Arrays.stream(scanner.nextLine().split(", ")).collect(Collectors.toList());
        String input = scanner.nextLine();

        while(!input.equalsIgnoreCase("Craft!")) {
            String[] commandParts = input.split(" - ");
            String command = commandParts[0];
            String commandItem = commandParts[1];

            switch (command){
                case "Collect":
                    if(!items.contains(commandItem)) {
                         items.add(commandItem);
                     }
                    break;
                case "Drop":
                    if(items.contains(commandItem)) {
                        items.remove(commandItem);
                    }
                    break;
                case "Combine Items":
                    String oldItem = commandItem.split(":")[0];
                    String newItem = commandItem.split(":")[1];
                    if(items.contains(oldItem)) {
                        items.add(items.indexOf(oldItem) + 1, newItem);
                    }
                    break;
                case "Renew":
                    if(items.contains(commandItem)) {
                        items.add(commandItem);
                        items.remove(commandItem);
                    }
                    break;
            }
            input = scanner.nextLine();
        }

        System.out.println(items.toString().join(", ",items));
    }
}
