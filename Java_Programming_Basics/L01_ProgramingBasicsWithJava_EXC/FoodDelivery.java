package L01_ProgramingBasicsWithJava_EXC;

import java.util.Scanner;

public class FoodDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double chicken = Double.parseDouble(scanner.nextLine()) * 10.35;
        double fish = Double.parseDouble(scanner.nextLine()) * 12.4;
        double vegetarian = Double.parseDouble(scanner.nextLine()) * 8.15;
        double totalMenus = chicken + fish + vegetarian;
        double dessert = totalMenus * 20 / 100;
        double total = totalMenus + dessert + 2.5;
        System.out.println(total);
    }
}
