package Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC;

import Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC.Box.Box;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int n = Integer.parseInt(scanner.nextLine());
        List<Box<String>> boxList = new ArrayList<>();

       for (int i = 0; i < n; i++) {
         String line = scanner.nextLine();
         Box<String> box = new Box<>(line);
         boxList.add(box);
        }

        boxList.forEach(System.out::println);
    }
}
