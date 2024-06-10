package Homeworks_And_Labs.L02_Java_OOP_Encapsulation_EXC.ShoppingSpree;

public class Product {
    private String name;
    private double cost;

    public Product(String name, double cost) {
        setName(name);
        setCost(cost);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        Validations.validateName(name);
        this.name = name;
    }



    public double getCost() {
        return cost;
    }

    private void setCost(double cost) {
       Validations.validateMoney(cost);
        this.cost = cost;
    }
}
