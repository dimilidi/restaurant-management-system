package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;


import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PokemonDontGo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> distances = Arrays
                .stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int sumRemovedElements = 0;

        while (!distances.isEmpty()) {
            int index = Integer.parseInt(scanner.nextLine());
            int removedElement;

            if (index < 0) {
                removedElement = distances.get(0);
                distances.set(0, distances.get(distances.size() - 1));
            } else if (index >= distances.size()) {
                removedElement = distances.get(distances.size() - 1);
                distances.set(distances.size() - 1, distances.get(0));
            } else {
                removedElement = distances.get(index);
                distances.remove(index);
            }

            for (int i = 0; i < distances.size(); i++) {
                int distancesElement = distances.get(i);

                if (distancesElement <= removedElement) {
                    distances.set(i, distancesElement + removedElement);
                } else {
                    distances.set(i, distancesElement - removedElement);
                }
            }

            sumRemovedElements += removedElement;
        }

        System.out.println(sumRemovedElements);
    }
}
