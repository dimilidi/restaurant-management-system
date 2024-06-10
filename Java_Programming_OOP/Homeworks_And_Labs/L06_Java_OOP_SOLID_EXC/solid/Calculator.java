package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid;

import java.util.Collection;

public abstract class Calculator<T> {

    public double sum(Iterable<T> values) {
        double sum = 0;

        for (T t : values) {
            sum += extractValue(t);
        }

        return sum;
    }


    public double average(Collection<T> values) {

        return sum(values) / values.size();
    }
    protected abstract double extractValue(T t);
}
