package ExamPreparation.Classes.SoftUniKindergarten_JavaAdvancedRegularExam18February2023;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<Child> getRegistry() {
        return registry;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setRegistry(List<Child> registry) {
        this.registry = registry;
    }

    public boolean addChild(Child child) {
        if(capacity > registry.size()) {
            registry.add(child);
            return true;
        }
        return  false;
    }

    public boolean removeChild(String firstName) {
       Child childToRemove =  getChild(firstName);

       if(childToRemove != null) {
           registry.remove(childToRemove);
           return true;
       }
       return false;
    }

    public int getChildrenCount() {
        return registry.size();
    }

    public Child getChild(String firstName) {
        return registry
                .stream()
                .filter(child -> child.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public String registryReport() {
        var children = registry.stream()
                .sorted(Comparator.comparing(Child::getAge)
                        .thenComparing(Child::getFirstName)
                        .thenComparing(Child::getLastName))
                .map(Child::toString)
                .collect(Collectors.toList());

        var sj = new StringJoiner(System.lineSeparator() + "--" + System.lineSeparator());
        sj.add("Registered children in " + name + ":");

        for (String child : children) {
            sj.add(child);
        }

        return  sj.toString().trim();
    }

}
