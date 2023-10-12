package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class LegendaryFarming {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean isWin = false;
        Map<String, Integer> materialsMap = new LinkedHashMap<>();
        Map<String, Integer> junkMap = new LinkedHashMap<>();


        while (!isWin) {
            if (isWin) {
                break;
            }
            int quantity = scanner.nextInt();
            String material = scanner.next().toLowerCase();

            if (isKeyMaterial(material)) {
                materialsMap.put(material, materialsMap.getOrDefault(material, 0) + quantity);
            } else {
                junkMap.put(material, junkMap.getOrDefault(material, 0) + quantity);
            }

            for (String item : materialsMap.keySet()) {
                if (materialsMap.get(item) >= 250) {
                    String obtainedItem = getObtainedItem(item);
                    System.out.println(obtainedItem + " obtained!");
                    materialsMap.put(item, materialsMap.get(item) - 250);
                    isWin = true;
                    break;
                }
            }

        }
       // Define the order of key materials
        List<String> keyMaterialsOrder = Arrays.asList("shards", "fragments", "motes");

       // Iterate over the ordered key materials
        for (String material : keyMaterialsOrder) {
            Integer quantity = materialsMap.get(material);
            System.out.println(material + ": " + (quantity != null ? quantity : 0));
        }


        // Print junk materials
        for (Map.Entry<String, Integer> entry : junkMap.entrySet()) {
            String material = entry.getKey();
            int quantity = entry.getValue();
            System.out.printf("%s: %d%n", material, quantity);
        }
    }

    public static boolean isKeyMaterial(String material) {
        return material.equals("shards") || material.equals("fragments") || material.equals("motes");
    }

    public static String getObtainedItem(String material) {
        if (material.equals("shards")) {
            return "Shadowmourne";
        } else if (material.equals("fragments")) {
            return "Valanyr";
        } else {
            return "Dragonwrath";
        }
    }
}
