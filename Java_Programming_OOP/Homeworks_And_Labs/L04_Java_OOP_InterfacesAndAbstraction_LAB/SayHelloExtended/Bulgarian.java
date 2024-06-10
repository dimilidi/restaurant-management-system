package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.SayHelloExtended;

public class Bulgarian extends BasePerson {
    public Bulgarian(String name) {
        super(name);
    }

    @Override
    public String sayHello() {
        return String.format("Здравей");
    }
}
