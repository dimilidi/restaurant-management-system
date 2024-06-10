package Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC.GenericBoxInteger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = Integer.parseInt(scanner.nextLine());
        List<GenericBoxInteger<Integer>> boxList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            Integer line = Integer.parseInt(scanner.nextLine());
            GenericBoxInteger<Integer> box = new GenericBoxInteger<>(line);
            boxList.add(box);
        }

        boxList.forEach(System.out::println);
    }
}
