package Homeworks_And_Labs.L09_Java_Advanced_IteratorsAndComparators_EXC.StrategyPattern;

import java.util.Comparator;

public class AgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return Integer.compare(p1.age, p2.age);
    }
}
