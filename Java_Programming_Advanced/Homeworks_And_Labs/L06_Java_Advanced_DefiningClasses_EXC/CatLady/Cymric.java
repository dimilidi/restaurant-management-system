package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.CatLady;

public class Cymric  extends Cat {

    private double furLength;

    public Cymric(String name, String type, double furLength) {
        super(name, type);
        this.furLength = furLength;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", super.getType(), super.getName(), this.furLength);
    }
}
