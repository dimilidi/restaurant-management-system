package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.ShoppingSpree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private double money;
    private List<Product> products;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.products = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validations.validateName(name);
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    private void setMoney(double money) {
       Validations.validateMoney(money);
       this.money = money;
    }

    public void buyProduct(Product product) {
        if (this.money >= product.getCost()) {
            this.products.add(product);
            this.money -= product.getCost();
        } else {
            throw new IllegalStateException(String.format("%s can't afford %s", getName(), product.getName()));
        }
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void print(Person person) {
        System.out.printf("%s - ",getName());
        if (this.products.isEmpty()) {
            System.out.println("Nothing bought");
        } else {
            List<String> productNames = getProducts()
                    .stream()
                    .map(Product::getName)
                    .collect(Collectors.toList());

            System.out.println(String.join(", ", productNames));
        }
    }
}
