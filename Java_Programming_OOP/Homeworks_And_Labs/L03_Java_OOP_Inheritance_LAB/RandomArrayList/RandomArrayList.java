package Homeworks_And_Labs.L03_Java_OOP_Inheritance_LAB.RandomArrayList;

import java.util.ArrayList;
import java.util.Random;

public class RandomArrayList extends ArrayList {
    public Object getRandomElement() {
        int index = new Random().nextInt(super.size());
        return super.remove(index);
    }
}
