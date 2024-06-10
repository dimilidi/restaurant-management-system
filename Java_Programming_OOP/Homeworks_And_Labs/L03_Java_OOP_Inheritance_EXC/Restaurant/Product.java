package Homeworks_And_Labs.L03_Java_OOP_Inheritance_EXC.Restaurant;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
