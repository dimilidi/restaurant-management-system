package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Train {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> wagons = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int maxCapacity = Integer.parseInt(scanner.nextLine());
        String input = scanner.nextLine();


        while (!input.equalsIgnoreCase("end")) {
            String[] commandParts = input.split(" ");
                if (input.contains("Add")) {
                    int wagonToAdd = Integer.parseInt(commandParts[1]);
                    wagons.add(wagonToAdd);
                } else {
                    int passengerToAdd = Integer.parseInt(commandParts[0]);
                    for (int i = 0; i < wagons.size(); i++) {
                        int currentWagon = wagons.get(i);
                        if ((currentWagon + passengerToAdd) <= maxCapacity) {
                            wagons.set(i, currentWagon + passengerToAdd);
                            break;
                        }
                    }
                }

                input = scanner.nextLine();
        }


        for (int wagon : wagons) {
            System.out.printf("%d ", wagon);
        }
    }
}
