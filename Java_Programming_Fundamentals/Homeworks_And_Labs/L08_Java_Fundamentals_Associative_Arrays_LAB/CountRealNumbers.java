package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_LAB;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CountRealNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] numbersArray = scanner.nextLine().split(" ");
        Map<Integer, Integer> numbersMap = new TreeMap<>();

        for (int i = 0; i < numbersArray.length; i++) {
            Integer number = Integer.parseInt(numbersArray[i]);
            if(!numbersMap.containsKey(number)) {
                numbersMap.put(number, 1);
            } else {
                numbersMap.put(number, numbersMap.get(number) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : numbersMap.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();

            System.out.printf("%d -> %d\n", key, value);
        }
    }
}
