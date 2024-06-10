package Regular_Exam_17Feb2024.ChickenSnack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class ChickenSnack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> moneyStack = new ArrayDeque<>();
        Deque<Integer> priceQueue = new ArrayDeque<>();

        fillStack(scanner, moneyStack);
        fillQueue(scanner, priceQueue);

        int eatenFood = 0;


        while(!moneyStack.isEmpty() && !priceQueue.isEmpty()) {
            int currentMoney = moneyStack.pop();
            int currentPrice = priceQueue.poll();

            if(currentMoney == currentPrice) {
                eatenFood++;
            } else if(currentMoney > currentPrice) {
                int diff = currentMoney - currentPrice;
                if(!moneyStack.isEmpty()) {
                    int nextMoney = moneyStack.pop();
                    nextMoney += diff;
                    moneyStack.push(nextMoney);
                }

                eatenFood++;
            }
        }


        if(eatenFood > 0) {
            if (eatenFood == 1) {
                System.out.printf("Henry ate: %d food.", eatenFood);
            }else if (eatenFood >= 4) {
                System.out.printf("Gluttony of the day! Henry ate %d foods.", eatenFood);
            } else {
                System.out.printf("Henry ate: %d foods.", eatenFood);
            }
        }


        if (eatenFood == 0) {
            System.out.printf("Henry remained hungry. He will try next weekend again.");
        }


    }

    private static void fillStack(Scanner scanner, Deque<Integer> stack) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(stack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<Integer> queue) {
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .forEach(queue::offer);
    }
}
