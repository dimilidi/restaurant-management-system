package Homeworks_And_Labs.L10_Java_Fundamentals_Regex_EXC;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SoftUniBarIncome {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        double totalIncome = 0.0;
        List<String> orderList = new ArrayList<>();

        while(!input.equalsIgnoreCase("end of shift")) {

            Pattern orderPattern = Pattern.compile("%(?<customer>[A-Z][a-z]+)%[^|$%.]*<(?<product>\\w+)>[^|$%.]*\\|(?<count>\\d+)\\|[^|$%.]*?(?<price>\\d+\\.?\\d+)\\$");
            Matcher matcher = orderPattern.matcher(input);
            boolean match = matcher.find();
            double totalPrice = 0.0;
            if(match){
                String customer = matcher.group("customer");
                String product = matcher.group("product");
                int count = Integer.parseInt( matcher.group("count"));
                double price =  Double.parseDouble((matcher.group("price")));
                totalPrice = count * price;
                totalIncome += totalPrice;
                String order = String.format("%s: %s - %.2f",customer, product, totalPrice);
                orderList.add(order);
            }

            input = scanner.nextLine();
        }

        for (String element : orderList) {
            System.out.println(element);
        }
        System.out.printf("Total income: %.2f", totalIncome);

    }
}



