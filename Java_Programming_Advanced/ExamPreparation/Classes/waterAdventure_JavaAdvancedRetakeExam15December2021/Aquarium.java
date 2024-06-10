package ExamPreparation.Classes.waterAdventure_JavaAdvancedRetakeExam15December2021;

import java.util.*;

public class Aquarium {
    private String name;
    private int capacity;
    private int size;
    private List<Fish> fishInPool;

    public Aquarium(String name, int capacity, int size) {
        this.name = name;
        this.capacity = capacity;
        this.size = size;
        this.fishInPool = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public int getFishInPool() {
        return fishInPool.size();
    }

    public void add(Fish fish) {
        if (fishInPool.size() < capacity) {
            if (findFish(fish.getName()) == null) {
                fishInPool.add(fish);
            }
        }
    }


    public boolean remove(String name) {
        Fish fishToRemove = findFish(name);
        if (fishToRemove != null) {
            fishInPool.remove(fishToRemove);
            return true;
        }
        return false;
    }

    public Fish findFish(String name) {
        return fishInPool
                .stream()
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public String report() {
        StringJoiner result = new StringJoiner("\n");
        result.add("Aquarium: " + getName() + " ^ " + "Size: " + getSize());
        for (Fish fish : fishInPool) {
            result.add(fish.toString());
        }
        return result.toString();
    }
}

