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
            int index  = Integer.parseInt(commandArr[1]);

            switch (command) {
                case "Shoot":
                        int target = targets.get(index);
                        int power = Integer.parseInt(commandArr[2]);
                        if (targets.indexOf(target) == index) {
                            if (target - power <= 0) {
                                targets.remove(index);
                            } else {
                                target -= power;
                                if(target > 0) {
                                     targets.set(index, target);
                                }
                            }
                        }
                    break;
                case "Add":
                    int elementToAdd = Integer.parseInt(commandArr[2]);
                    if (index <= targets.size() - 1) {
                        targets.add(index, elementToAdd);
                    } else {
                        System.out.println("Invalid placement!");
                    }
                    break;
                case "Strike":
                    int radius = Integer.parseInt(commandArr[2]);
                    int leftRange = index - radius;
                    int rightRange = index + radius;
                    if (rightRange <= targets.size() - 1 && leftRange >= 0) {
                        for (int i = rightRange; i >= leftRange; i--) {
                            targets.remove(i);
                        }
                    } else {
                        System.out.println("Strike missed!");
                    }
                    break;

            }
           input = scanner.nextLine();
        }


        for(Integer target : targets) {
            if(targets.indexOf(target) == (targets.size() - 1)) {
                System.out.println(target);
            } else {
                 System.out.print(target + "|");
            }
        }
    }
}
