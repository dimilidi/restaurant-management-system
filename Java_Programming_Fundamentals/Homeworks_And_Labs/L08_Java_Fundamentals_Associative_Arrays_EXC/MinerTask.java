package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class MinerTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        int countLines = 0;

        List<String[]> pairsList = new ArrayList<>();
        Map<String, String> resourcesMap = new LinkedHashMap<>();

        String quantity = "";
        String resource = "";


        while (!input.equalsIgnoreCase("stop")) {
            countLines++;

            if (countLines % 2 != 0) {
                resource = input;
            } else {
               quantity = input;
               String[] pair = {resource, quantity};
                boolean isDouble = false;
                for (int i = 0; i < pairsList.size(); i++) {
                    String currQuantity = input;

                    if(pairsList.get(i)[0].equals(resource)) {
                        isDouble = true;
                        int quantityInt = Integer.parseInt(pairsList.get(i)[1]);
                        int newQuantity = Integer.parseInt(currQuantity) + quantityInt;
                        currQuantity = String.valueOf(newQuantity);
                        quantity = currQuantity;
                        String[] newPair = {resource, currQuantity};
                        pairsList.set(i,newPair);
                    }
                }

                if(isDouble) {
                    pairsList.remove(pair);
                } else {
                    pairsList.add(pair);
                }
            }
            input = scanner.nextLine();
        }

        for (int i = 0; i < pairsList.size(); i++) {
            String key = pairsList.get(i)[0];
            String value =  pairsList.get(i)[1];
            resourcesMap.put(key, value);
        }


        for (Map.Entry<String, String> stringStringEntry : resourcesMap.entrySet()) {
            System.out.println(stringStringEntry.getKey() + " -> " + stringStringEntry.getValue());
        }

    }
}
