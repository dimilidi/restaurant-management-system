package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class SetsOfElementsFilter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] length = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = length[0];
        int m = length[1];

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        fillSet(scanner, n, firstSet);
        fillSet(scanner, m, secondSet);

        firstSet.stream()
                .filter(secondSet::contains)
                .forEach( el -> System.out.print(el + " "));

    }
    private static void fillSet(Scanner scanner, int n, Set<Integer> numSet) {
        for (int i = 0; i < n; i++) {
            Integer num =  Integer.parseInt(scanner.nextLine());
            numSet.add(num);
        }
    }
}
