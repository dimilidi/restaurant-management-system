package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class PeriodicTableFor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int compoundsCount = Integer.parseInt(scanner.nextLine());

        Set<String> elementsSet = new TreeSet<>();

        for (int i = 0; i < compoundsCount; i++) {
            String[] compound = scanner.nextLine().split("\\s+");
            for (int j = 0; j < compound.length; j++) {
                elementsSet.add(compound[j]);
            }
        }
        elementsSet.forEach(el -> System.out.print(el + " "));
    }
}
