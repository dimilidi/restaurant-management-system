package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.CatLady;

public class StreetExtraordinaire extends Cat {

    private double decibelsOfMeows;

    public StreetExtraordinaire(String name, String type, double decibelsOfMeows) {
        super(name, type);
        this.decibelsOfMeows = decibelsOfMeows;
    }

    @Override
    public String toString() {
        return String.format("%s %s %.2f", super.getType(), super.getName(), this.decibelsOfMeows);
    }
}