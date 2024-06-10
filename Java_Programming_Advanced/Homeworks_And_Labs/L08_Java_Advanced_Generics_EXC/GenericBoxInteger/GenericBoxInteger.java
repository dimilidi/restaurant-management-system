package Homeworks_And_Labs.L08_Java_Advanced_Generics_EXC.GenericBoxInteger;

public class GenericBoxInteger <T> {
    private T data;

    public GenericBoxInteger(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s: %s",
                this.data.getClass().getName(),
                this.data);
    }
}
