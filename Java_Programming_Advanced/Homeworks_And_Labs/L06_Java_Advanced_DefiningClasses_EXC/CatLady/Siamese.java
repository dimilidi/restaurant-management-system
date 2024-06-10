package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.CatLady;

public class Siamese  extends Cat {

    private double earSize;

    public Siamese(String name, String type, double earSize) {
            super(name, type);
            this.earSize = earSize;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", super.getType(), super.getName(), this.earSize);
    }

}
