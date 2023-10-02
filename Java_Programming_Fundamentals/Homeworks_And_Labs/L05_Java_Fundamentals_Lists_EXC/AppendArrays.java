package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AppendArrays {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] arr = scanner.nextLine().trim().split("\\|");
        List<String> arrList = Arrays.stream(arr).collect(Collectors.toList());
        List<String> reversedArrList = new ArrayList<>();

        for (int i = 0; i < arrList.size(); i++) {
           reversedArrList.add(0, arrList.get(i).trim());
        }

        reversedArrList.toString().replace("  ", " ");
        System.out.println(reversedArrList.toString()
                .replace("[", "")
                .replace(",", "")
                .replace(", ", "")
                .replace("]", "")
                .replace("  ", " ")
                .replaceAll("\\s+", " ")
                .trim()
        );

    }
}
