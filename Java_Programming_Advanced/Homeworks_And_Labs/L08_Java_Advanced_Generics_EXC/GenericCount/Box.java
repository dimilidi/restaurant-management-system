package Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC.GenericCount;
public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {

    private T data;

    public Box(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.data.getClass().getName(),
                this.data);
    }

    @Override
    public int compareTo(Box<T> o) {
        return this.data.compareTo(o.data);
    }
}

