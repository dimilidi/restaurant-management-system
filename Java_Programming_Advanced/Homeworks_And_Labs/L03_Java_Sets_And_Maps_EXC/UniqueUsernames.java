package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Set<String> namesSet = new LinkedHashSet<>();

        fillSetForI(scanner, n, namesSet);

        namesSet.forEach(System.out::println);
    }

    private static void fillSetForI(Scanner scanner, int n, Set<String> namesSet) {
        for (int i = 0; i < n; i++) {
            String username = scanner.nextLine();
            namesSet.add(username);
        }
    }
}
