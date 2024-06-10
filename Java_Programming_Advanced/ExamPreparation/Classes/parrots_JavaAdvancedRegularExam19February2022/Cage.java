package ExamPreparation.Classes.parrots_JavaAdvancedRegularExam19February2022;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Cage {
    private String name;
    private int capacity;
    private List<Parrot> data = new ArrayList<>();

    public Cage(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public void add(Parrot parrot) {
        if(data.size() < capacity) {
            data.add(parrot);
        }
    }

/*    public boolean remove(String name) {
        int initialDataSize = data.size();
       data.stream()
             .filter((parrot) -> !parrot.getName().equals(name))
             .collect(Collectors.toList());

         return initialDataSize != data.size();
    }*/

    public boolean remove(String name) {
        for (int i = 0; i < this.data.size(); i++) {
            if (this.data.get(i).getName().equals(name)) {
                this.data.remove(i);
                return true;
            }
        }
        return false;
    }

    public Parrot sellParrot(String name) {
        Parrot parrot = data.stream()
            .filter((p) -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        if(parrot != null && parrot.isAvailable()) {
            parrot.setIsAvailable(false);
        }
        return parrot;
    }


    public List<Parrot> sellParrotBySpecies(String species) {
        List<Parrot> parrotsToSell = new ArrayList<>();
        for (Parrot p : data) {
            if (p.getSpecies().equals(species)) {
                p.setIsAvailable(false);
                parrotsToSell.add(p);
            }
        }
        return parrotsToSell;
    }

    public int count() {
        return data.size();
    }

    public String report() {
       List<Parrot> availableParrots = data
               .stream()
               .filter((p -> p.isAvailable()))
               .collect(Collectors.toList());
        var result = new StringJoiner("\n");
        result.add(  "Parrots available at " + getName() + ":");

        for (Parrot parrot : availableParrots) {
            result.add(parrot.toString());
        }

        return result.toString().trim();
    }

}
