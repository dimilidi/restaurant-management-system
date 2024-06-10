package ExamPreparation.Classes.easterBasket_JavaAdvancedRetakeExam13April2022;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;


public class Basket {
    private List<Egg> data = new ArrayList<>();
    private String material;
    private int capacity;

    public Basket(String material, int capacity) {
        this.data = new ArrayList<>();
        this.material = material;
        this.capacity = capacity;
    }

    public void addEgg(Egg egg) {
        if(data.size() < capacity) {
            data.add(egg);
        }
    }

    public boolean removeEgg(String color) {
     /*   int initialSize = data.size();
        data.stream()
                .filter(egg -> !egg.getColor().equals(color))
                .collect(Collectors.toList());
        return data.size() != initialSize;*/

        for (Egg egg : data) {
            if(egg.color.equals(color)) {
               return data.remove(egg);
            }
        }
        return false;
    }

    public Egg getStrongestEgg() {
        if(data.isEmpty()) {
            return null;
        }
        Egg strongestEgg = data.get(0);
        for (Egg egg : data) {
            if(egg.getStrength() > strongestEgg.getStrength()) {
                strongestEgg = egg;
            }
        }
        return strongestEgg;
    }

  /*  public Egg getStrongestEggStream() {
       return data.stream().reduce(
               (egg1, egg2) -> egg1.getStrength() > egg2.getStrength() ? egg1 : egg2
       ).orElse(null);
    }*/

    public Egg getEgg(String color) {
        return data
                .stream()
                .filter((egg) -> egg.getColor().equals(color))
                .findFirst()
                .orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String report() {
        var result = new StringJoiner("\n");
        result.add(material + " basket contains:");
        for (Egg egg : data) {
            result.add(egg.toString());
        }
        return result.toString();
    }
}
