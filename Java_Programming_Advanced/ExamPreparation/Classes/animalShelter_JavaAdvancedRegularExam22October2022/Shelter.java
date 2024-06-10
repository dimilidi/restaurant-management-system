package ExamPreparation.Classes.animalShelter_JavaAdvancedRegularExam22October2022;

import java.util.*;

public class Shelter {
    private int capacity;
    private List<Animal> data;

    public Shelter(int capacity) {
        this.capacity = capacity;
        this.data =  new ArrayList<>();
    }

    public void add(Animal animal) {
        if(data.size() < capacity) {
            data.add(animal);
        }
    }
    public boolean remove(String name) {
        for (Animal animal : data) {
            if(animal != null && animal.getName().equals(name)) {
                data.remove(animal);
                return true;
            }
        }
        return false;
    }

    public Animal getAnimal(String name, String caretaker) {
        Animal result = null;
        for (Animal animal : data) {
            if (animal.getName().equals(name) && animal.getCaretaker().equals(caretaker)) {
                result = animal;
            }
        }
        return result;
    }

    public Animal getOldestAnimal() {
        return this.data.stream()
                .max(Comparator.comparingInt(Animal::getAge))
                .orElse(null);
    }

    public int getCount() {
        return data.size();
    }

    public String getStatistics() {
        var sj = new StringJoiner("\n");
        sj.add("The shelter has the following animals:");
        for (Animal animal : data) {
            sj.add(animal.getName() + " " +  animal.getCaretaker());
        }
        return sj.toString();
    }
}
