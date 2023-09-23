package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_LAB;

import java.util.*;

public class FruitShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fruit = scanner.nextLine();
        String day = scanner.nextLine().toLowerCase();
        double amount = Double.parseDouble(scanner.nextLine());

        List<String> workingDays = new ArrayList<>();
        workingDays.add("monday");
        workingDays.add("tuesday");
        workingDays.add("wednesday");
        workingDays.add("thursday");
        workingDays.add("friday");

        List<String> weekend = new ArrayList<>();
        weekend.add("saturday");
        weekend.add("sunday");

        Map<String, Double> myMap = new HashMap<>();
        myMap.put("banana", 2.50);
        myMap.put("apple", 1.20);
        myMap.put("orange", 0.85);
        myMap.put("grapefruit", 1.45);
        myMap.put("kiwi", 2.70);
        myMap.put("pineapple", 5.50);
        myMap.put("grapes", 3.85);

        for (Map.Entry<String, Double> entry : myMap.entrySet()) {
            if (myMap.containsKey(fruit) && weekend.contains(day)){
                myMap.put("banana", 2.70);
                myMap.put("apple", 1.25);
                myMap.put("orange", 0.90);
                myMap.put("grapefruit", 1.60);
                myMap.put("kiwi", 3.00);
                myMap.put("pineapple", 5.60);
                myMap.put("grapes", 4.20);
            }
        }

        if(myMap.containsKey(fruit) && (weekend.contains(day) || workingDays.contains(day))) {
            System.out.printf("%.2f",myMap.get(fruit) * amount);
        } else {
            System.out.println("error");
        }
    }
}
