package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.GroomingSalon;

import java.util.ArrayList;
import java.util.List;

public class GroomingSalon {
    private List<Pet> data;
    private int capacity;

    public GroomingSalon(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Pet pet) {
        if(this.capacity > this.data.size()) {
            this.data.add(pet);
        }
    }

    public boolean remove(String name) {
        boolean isRemoved = false;
        for (int i = 0; i < this.data.size() ; i++) {
            Pet pet = this.data.get(i);
            if(pet.getName().equals(name)) {
                isRemoved = true;
                this.data.remove(pet);
            }

        }
        return isRemoved;
    }

    public Pet getPet(String name, String owner){
        for (Pet pet : this.data) {
            if (pet.getName().equals(name) && pet.getOwner().equals(owner)) {
                return pet;
            }
        }
        return null;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        String output = "";

        output += "The grooming salon has the following clients:\n";

        for (Pet pet : this.data) {
            output += pet.getName() + " " + pet.getOwner() + "\n";
        }

        return output;
    }
}
