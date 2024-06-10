package Homeworks_And_Labs.L05_Java_Advanced_FunctionalProgramming_EXC;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;

public class KnightsOfHonor {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Consumer<String> printWithSir = name -> System.out.println("Sir " + name);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .forEach(printWithSir);

    }
}
