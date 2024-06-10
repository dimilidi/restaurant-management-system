package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PeriodicTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int compoundsCount = Integer.parseInt(scanner.nextLine());

        Set<String> elementsSet = new TreeSet<>();

        for (int i = 0; i < compoundsCount; i++) {
           fillSet(scanner, elementsSet);
        }
        String result = String.join(" ", elementsSet);
        System.out.println(result);
    }
    private static  void fillSet(Scanner scanner, Set<String> set) {
        set.addAll(Arrays.asList(scanner.nextLine().split("\\s+")));
    }

}
