package Homeworks_And_Labs.L05_Java_Advanced_FunctionalProgramming_EXC;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Predicate<String> withLengthSmallerThanN = s -> s.length() <= n;

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .filter(withLengthSmallerThanN)
                .forEach(System.out::println);
    }
}
