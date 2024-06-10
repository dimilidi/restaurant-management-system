package Homeworks_And_Labs.L05_Java_Advanced_FunctionalProgramming_EXC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindTheSmallestElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers =  Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Function<List<Integer>, Integer> findIndexOfSmallestNumber = list -> list.lastIndexOf(Collections.min(list));
        int lastIndex = findIndexOfSmallestNumber.apply(numbers);
        System.out.println(lastIndex);

    }
}
