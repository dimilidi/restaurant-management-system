package Homeworks_And_Labs.L06_Java_Fundamentals_Mid_Exam_Preparation_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MovingTarget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> targets = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("end")) {
            String[] commandArr = input.split(" ");
            String command = commandArr[0];
            int index = Integer.parseInt(commandArr[1]);

            switch (command) {
                case "Shoot":
                    if (isValidIndex(index, targets.size())) {
                        int power = Integer.parseInt(commandArr[2]);
                        int target = targets.get(index);
                        target -= power;
                        if (target <= 0) {
                            targets.remove(index);
                        } else {
                            targets.set(index, target);
                        }
                    }
                    break;
                case "Add":
                    int elementToAdd = Integer.parseInt(commandArr[2]);
                    if (isValidIndex(index, targets.size())) {
                        targets.add(index, elementToAdd);
                    } else {
                        System.out.println("Invalid placement!");
                    }
                    break;
                case "Strike":
                    int radius = Integer.parseInt(commandArr[2]);
                    if (isValidIndex(index, targets.size()) && isValidIndex(index - radius, targets.size()) && isValidIndex(index + radius, targets.size())) {
                        for (int i = index + radius; i >= index - radius; i--) {
                            targets.remove(i);
                        }
                    } else {
                        System.out.println("Strike missed!");
                    }
                    break;
            }
            input = scanner.nextLine();
        }

        String result = targets.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("|"));

        System.out.println(result);
    }

    private static boolean isValidIndex(int index, int size) {
        return index >= 0 && index < size;
    }
}
