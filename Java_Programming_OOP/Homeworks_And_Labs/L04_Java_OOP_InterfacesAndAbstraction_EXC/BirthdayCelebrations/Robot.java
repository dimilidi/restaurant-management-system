package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.BirthdayCelebrations;

public class Robot implements Identifiable{
    private String id;
    private String model;

    public Robot(String id, String model) {
        this.id = id;
        this.model = model;
    }

    public String getModel() {
        return model;
    }
    @Override
    public String getId() {
        return id;
    }
}
