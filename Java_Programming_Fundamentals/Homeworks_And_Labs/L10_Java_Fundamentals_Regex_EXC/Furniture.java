package Homeworks_And_Labs.L10_Java_Fundamentals_Regex_EXC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Furniture {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String item = "";
        double price = 0.0;
        int quantity = 0;
        double totalPrice = 0.0;
        List<String> furnitureList = new ArrayList<>();


        while(!input.equals("Purchase")) {
            Pattern pattern = Pattern.compile(">>(?<item>\\w+)<<(?<price>\\d+(\\.\\d+)?)!(?<quantity>[0-9]+)");
            Matcher matcher = pattern.matcher(input);
            boolean match = matcher.find();
            if(match){
                item = matcher.group("item");
                price =  Double.parseDouble((matcher.group(String.valueOf("price"))));
                quantity = Integer.parseInt( matcher.group("quantity"));
                furnitureList.add(item);
                totalPrice += quantity * price;
            }
            input = scanner.nextLine();
        }

        System.out.println("Bought furniture: ");

        for (String furniture : furnitureList) {
            System.out.println(furniture);
        }
        System.out.printf("Total money spend: %.2f", totalPrice);

    }
}
