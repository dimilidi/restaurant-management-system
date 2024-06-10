package Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC.GenericSwap;
public class Box<T> {

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
}

