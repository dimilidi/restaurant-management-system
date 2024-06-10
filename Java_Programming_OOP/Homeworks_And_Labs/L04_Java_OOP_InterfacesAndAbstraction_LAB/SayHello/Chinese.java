package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.SayHello;

public class Chinese implements Person {
    private String name;

    public Chinese(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String sayHello() {
        return String.format("Djydjybydjy");
    }

}
