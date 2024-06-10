package ExamPreparation.DataStructures.LinearDataStructures;

import java.util.*;

public class ItsChocolateTime_JavaAdvancedRegularExam25June2022 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Double> milkQueue = new ArrayDeque<>();
        Deque<Double> cacaoStack = new ArrayDeque<>();
        Map<String, Double> chocolateMap = new LinkedHashMap<>();
        Map<String, Integer> producedChocoMap = new TreeMap<>();

        chocolateMap.put("Milk Chocolate", 30.0);
        chocolateMap.put("Dark Chocolate", 50.0);
        chocolateMap.put("Baking Chocolate", 100.0);

        fillQueue(scanner, milkQueue);
        fillStack(scanner, cacaoStack);


        while(!milkQueue.isEmpty() && !cacaoStack.isEmpty()) {
            Double currentCacao = cacaoStack.pop();
            Double currentMilk = milkQueue.poll();
            Double currentCacaoPercentage = (currentCacao / (currentCacao + currentMilk)) * 100;

            for (Map.Entry<String, Double> chocoEntry : chocolateMap.entrySet()) {
                String chocolate = chocoEntry.getKey();
                Double cacao = chocoEntry.getValue();

                if(currentCacaoPercentage.equals(cacao)) {
                    if(!producedChocoMap.containsKey(chocolate)) {
                        producedChocoMap.put(chocolate, 0);
                    }
                    producedChocoMap.put(chocolate, producedChocoMap.get(chocolate) + 1);
                } else {
                    currentMilk += 10;
                    milkQueue.offer(currentMilk);
                }
            }
        }


    if(producedChocoMap.size() == 3) {
        System.out.println("It’s a Chocolate Time. All chocolate types are prepared.");
    } else {
        System.out.println("Sorry, but you didn't succeed to prepare all types of chocolates.");
    }

    producedChocoMap.entrySet().stream().forEach(c -> System.out.printf("# %s --> %d%n", c.getKey(), c.getValue()));

    }

    private static void fillStack(Scanner scanner, Deque<Double> stack) {
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .forEach(stack::push);
    }

    private static void fillQueue(Scanner scanner, Deque<Double> queue) {
        Arrays.stream(scanner.nextLine().split(" "))
                .map(Double::parseDouble)
                .forEach(queue::offer);
    }
}
