package ExamPreparation.Classes.vendingSystem_JavaAdvancedRetakeExam9August2023;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class VendingMachine {
    private int buttonCapacity;
    private List<Drink> drinks;

    public VendingMachine(int buttonCapacity) {
        this.buttonCapacity = buttonCapacity;
        this.drinks = new ArrayList<>();
    }

    public int getCount() {
        return drinks.size();
    }

    public void addDrink(Drink drink) {
        if(buttonCapacity > drinks.size()) {
            drinks.add(drink);
        }
    }

    public boolean removeDrink(String name) {
        Drink drinkToRemove = drinks
                .stream()
                .filter(drink -> drink.getName().equals(name))
                .findFirst()
                .orElse(null);
        if(drinkToRemove != null) {
            drinks.remove(drinkToRemove);
            return true;
        }
        return  false;
    }

    public Drink getLongest() {
       int maxVolume =  drinks.stream().mapToInt(d -> d.getVolume()).max().orElse(0);
       return drinks.stream().filter(drink -> drink.getVolume() >= maxVolume).findFirst().get();
    }

    public Drink getCheapest() {
        BigDecimal minPrice = drinks.stream()
                .map(Drink::getPrice)
                .min(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return drinks.stream()
                .filter(d -> d.getPrice().equals(minPrice))
                .findFirst()
                .orElse(null);
    }

    public String buyDrink(String name) {
        Drink drink =  drinks.stream().filter(d -> d.getName().equals(name)).findFirst().orElse(null);
        return drink.toString();
    }

    public String report() {
        var sj = new StringJoiner("\n");
        sj.add("Drinks available:");
        for (Drink drink : drinks) {
            sj.add(drink.toString());
        }
        return sj.toString();
    }

}
