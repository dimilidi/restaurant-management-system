package Homeworks_And_Labs.L03_Java_Sets_And_Maps_LAB;

import java.util.*;
import java.util.stream.Collectors;

public class Utils {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Set<Integer> set = new TreeSet<>();


        fillSetAll(scanner, set);
        printSet(set);


        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Lidiya");
        map.put(2, "Peter");

        printMap(map);
        printMap(map);


    }
    // PRINT MAP
    private static void printMap(Map<Integer, String> map) {
        map.entrySet()
                .forEach((entry -> System.out.println(entry.getKey() + " " + entry.getValue())));
    }

    private static void mapPrint(Map<Integer, String> map) {
        map.forEach((k, v)-> System.out.println(k + " " + v));
    }

    // PRINT SET
    private static void printSet(Set<Integer> set) {
        set.forEach(System.out::println);
    }


    //READ SET
    private static void fillSet(Scanner scanner, Set<Integer> set) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(set::add);
    }

    private static  void fillSetAll(Scanner scanner, Set<Integer> set) {
        set.addAll(Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList()));
    }

    private static  void fillSetString(Scanner scanner, Set<String> set) {
        set.addAll(Arrays.asList(scanner.nextLine().split("\\s+")));
    }

    private static void fillHashSet(Scanner scanner, Set<Integer> set) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

}
