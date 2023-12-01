package midExam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CoffeeLover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> coffeeStock = new ArrayList<>();
        String[] initialStock = scanner.nextLine().split(" ");
        coffeeStock.addAll(List.of(initialStock));

        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            String[] command = scanner.nextLine().split(" ");
            String action = command[0];

            switch (action) {
                case "Include":
                    String coffeeToAdd = command[1];
                    coffeeStock.add(coffeeToAdd);
                    break;
                case "Remove":
                    String removeType = command[1];
                    int numberOfCoffees = Integer.parseInt(command[2]);
                    if (removeType.equals("first")) {
                        if (numberOfCoffees < coffeeStock.size()) {
                            coffeeStock.subList(0, numberOfCoffees).clear();
                        }
                    } else if (removeType.equals("last")) {
                        if (numberOfCoffees < coffeeStock.size()) {
                            int startIndex = coffeeStock.size() - numberOfCoffees;
                            coffeeStock.subList(startIndex, coffeeStock.size()).clear();
                        }
                    }
                    break;
                case "Prefer":
                    int coffeeIndex1 = Integer.parseInt(command[1]);
                    int coffeeIndex2 = Integer.parseInt(command[2]);
                    if (isValidIndex(coffeeIndex1, coffeeStock) && isValidIndex(coffeeIndex2, coffeeStock)) {
                        String temp = coffeeStock.get(coffeeIndex1);
                        coffeeStock.set(coffeeIndex1, coffeeStock.get(coffeeIndex2));
                        coffeeStock.set(coffeeIndex2, temp);
                    }
                    break;
                case "Reverse":
                    reverseList(coffeeStock);
                    break;
            }
        }

        System.out.println("Coffees:");

        System.out.println(String.join(" ", coffeeStock));
    }

    private static boolean isValidIndex(int index, List<String> list) {
        return index >= 0 && index < list.size();
    }

    private static void reverseList(List<String> list) {
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            String temp = list.get(left);
            list.set(left, list.get(right));
            list.set(right, temp);
            left++;
            right--;
        }
    }
}


//Arabica Liberica Chariarina Magnistipula Robusta BulkCoffee StrongCoffee
//5
//Include TurkishCoffee
//Remove first 2
//Remove last 1
//Prefer 3 1
//Reverse

//Arabica Robusta BulkCoffee StrongCoffe TurkishCoffeee
//5
//Include OrdinaryCoffee
//Remove first 1
//Prefer 0 1
//Prefer 3 1
//Reverse