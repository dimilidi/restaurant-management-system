package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MonsterExtermination_JavaAdvancedRetakeExam9August2023 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> monsterArmorQueue =  new ArrayDeque<>();
        Deque<Integer> soldierStrengthsStack =  new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .forEach(monsterArmorQueue::offer);

        Arrays.stream(scanner.nextLine().split(","))
                .map(Integer::parseInt)
                .forEach(soldierStrengthsStack::push);

        int killedMonsters = 0;

        while(!monsterArmorQueue.isEmpty() && !soldierStrengthsStack.isEmpty()) {
            int monster = monsterArmorQueue.poll();
            int soldier = soldierStrengthsStack.pop();

            if(soldier >= monster) {
                killedMonsters++;
                soldier = soldier - monster;

                if(soldier > 0) {
                    if(!soldierStrengthsStack.isEmpty()) {
                        soldier += soldierStrengthsStack.pop();
                    }
                    soldierStrengthsStack.push(soldier);
                }

            } else {
                monster = monster - soldier;
                monsterArmorQueue.offer(monster);
            }

       }

        if(monsterArmorQueue.isEmpty()) {
            System.out.println("All monsters have been killed!");
        }

        if (soldierStrengthsStack.isEmpty()) {
            System.out.println("The soldier has been defeated.");
        }

        System.out.printf("Total monsters killed: %d", killedMonsters);
    }
}
