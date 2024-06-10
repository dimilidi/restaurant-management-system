package ExamPreparation.Classes.vendingSystem_JavaAdvancedRetakeExam9August2023;

import java.math.BigDecimal;

public class Drink {
    private String name;
    private BigDecimal price;
    private  int volume;

    public Drink(String name, BigDecimal price, int volume) {
        this.name = name;
        this.price = price;
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return  price;
    }

    public int getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Price: $%s, Volume: %d ml", name, price.toString(), volume);
    }
}
