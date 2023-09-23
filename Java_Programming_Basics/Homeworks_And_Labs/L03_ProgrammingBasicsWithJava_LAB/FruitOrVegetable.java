package Homeworks_And_Labs.L03_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class FruitOrVegetable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String productName = scanner.nextLine().toLowerCase();
        String productType = "unknown";

        switch(productName){
            case "banana":
            case "apple":
            case "kiwi":
            case "grapes":
            case "cherry":
            case "lemon":
                productType = "fruit";
                break;
            case "tomato":
            case "cucumber":
            case "pepper":
            case "carrot":
                productType = "vegetable";
                break;
            default:
                productType = "unknown";
        }

        System.out.println(productType);
    }
}
