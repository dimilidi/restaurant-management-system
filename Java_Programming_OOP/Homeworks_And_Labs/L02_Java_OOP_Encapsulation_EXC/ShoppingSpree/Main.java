package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.ShoppingSpree;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> peopleList;
        List<Product> productList;

        try {
            peopleList = parsePeople(scanner.nextLine());
            productList = parseProducts(scanner.nextLine());
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }


        String command = scanner.nextLine();
        while (!command.equals("END")) {
            String[] commandParts = command.split("\\s+");

            Person person = peopleList.stream().filter(p -> p.getName().equals(commandParts[0])).findFirst().get();
            Product product = productList.stream().filter(p -> p.getName().equals(commandParts[1])).findFirst().get();
            try {
                person.buyProduct(product);
                System.out.printf("%s bought %s%n", person.getName(), product.getName());
            } catch(IllegalStateException e) {
                System.out.println(e.getMessage());
            }


            command = scanner.nextLine();
        }
        for (Person person : peopleList) {
            person.print(person);
        }
    }

    private static List<Product> parseProducts(String productString) {
        List<Product> productList = new ArrayList<>();
        String[] products = productString.split(";");
        for (String product : products) {
            String[] productInfo = product.split("=");
            Product currentProduct = new Product(productInfo[0], Double.parseDouble(productInfo[1]));
            productList.add(currentProduct);
        }

        return productList;
    }

    private static List<Person> parsePeople(String personString) {
        List<Person> peopleList = new ArrayList<>();
        String[] peopleAsStrings = personString.split(";");
        for (String p : peopleAsStrings) {
            String[] peopleInfo = p.split("=");
            Person person = new Person(peopleInfo[0], Double.parseDouble(peopleInfo[1]));
            peopleList.add(person);
        }

        return peopleList;
    }
}
