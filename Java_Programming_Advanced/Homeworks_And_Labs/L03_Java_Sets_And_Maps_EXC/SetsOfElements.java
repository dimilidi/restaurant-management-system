package Homeworks_And_Labs.L03_Java_Sets_And_Maps_EXC;

import java.util.*;

import static java.lang.Math.min;

public class SetsOfElements {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] length = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int n = length[0];
        int m = length[1];

        Set<Integer> firstSet = new LinkedHashSet<>();
        Set<Integer> secondSet = new LinkedHashSet<>();

        fillSet(scanner, n, firstSet);
        fillSet(scanner, m, secondSet);

        if(secondSet.size() <= firstSet.size() ) {
            for (int el :  secondSet) {
                if(firstSet.contains(el)) {
                    System.out.print(el + " ");
                }
            }
        } else {
            for (int el :  firstSet) {
                if(secondSet.contains(el)) {
                    System.out.print(el + " ");
                }
            }
        }

    }
    private static void fillSet(Scanner scanner, int n, Set<Integer> numSet) {
        for (int i = 0; i < n; i++) {
            Integer num =  Integer.parseInt(scanner.nextLine());
            numSet.add(num);
        }
    }
}
