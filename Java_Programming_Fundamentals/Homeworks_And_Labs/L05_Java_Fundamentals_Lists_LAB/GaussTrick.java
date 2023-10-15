package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_LAB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GaussTrick {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> nums  = Arrays.stream(scanner.nextLine().split(" "))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> result = new ArrayList<>();

        if(nums.size() % 2 != 0){
            int midIdx = nums.size() / 2;
            nums.add(midIdx, 0);
        }

        for (int i = 0; i < nums.size() / 2; i++) {
            int leftElement = nums.get(i);
            int rightElement = nums.get(nums.size() - i -1);
            result.add(leftElement + rightElement);
        }

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
