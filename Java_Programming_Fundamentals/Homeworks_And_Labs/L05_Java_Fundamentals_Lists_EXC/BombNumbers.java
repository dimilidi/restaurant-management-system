package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BombNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays
                .stream((scanner.nextLine()
                .split(" ")))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int[] bombNumber = Arrays
                .stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int specialBombNumber = bombNumber[0];
        int bombPower = bombNumber[1];
        int sum = 0;

        while (numbers.contains(specialBombNumber)) {
            int specialNumberIndex = numbers.indexOf(specialBombNumber);
            int leftBound = Math.max(0, specialNumberIndex - bombPower);
            int rightBound = Math.min(numbers.size() - 1, specialNumberIndex + bombPower);

            for (int i = rightBound; i >= leftBound; i--) {
                numbers.remove(i);
            }
        }

        for(int number : numbers){
            sum += number;
        }

        System.out.println(sum);
    }
}
