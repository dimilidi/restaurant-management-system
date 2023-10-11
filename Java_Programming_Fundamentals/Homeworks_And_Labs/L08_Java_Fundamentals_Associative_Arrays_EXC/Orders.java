package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Orders {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        Map<String, Double[]> productsMap = new LinkedHashMap<>();


        while (!input.equalsIgnoreCase("buy")) {
            String[] inputArray = input.trim().split(" ");
            String name = inputArray[0];
            double price = Double.parseDouble(inputArray[1]);
            double quantity = Double.parseDouble(inputArray[2]);
            Double[] priceAndQuantityArr = {price, quantity};

            if (!productsMap.containsKey(name)) {
                productsMap.put(name, priceAndQuantityArr);
            } else {
                Double[] existingPriceAndQuantity = productsMap.get(name);
                existingPriceAndQuantity[0] = price;
                existingPriceAndQuantity[1] += quantity;
            }

            input = scanner.nextLine();
        }

        for (Map.Entry<String, Double[]> stringEntry : productsMap.entrySet()) {
            String product = stringEntry.getKey();
            Double totalPrice = stringEntry.getValue()[0] * stringEntry.getValue()[1];
            System.out.printf("%s -> %.2f\n", product, totalPrice);
        }
    }
}
