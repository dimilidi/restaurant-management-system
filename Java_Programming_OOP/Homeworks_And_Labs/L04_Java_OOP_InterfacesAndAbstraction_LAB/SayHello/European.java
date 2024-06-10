package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.SayHello;

public class European implements Person {
    private String name;

    public European(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String sayHello() {
        return String.format("Hello");
    }

}
