package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.FoodShortage;

public class Rebel implements Person,  Buyer{
    private String name;
    private int age;
    private String group;
    private int food;

    public Rebel(String name, int age, String group) {
        this.name = name;
        this.age = age;
        this.group = group;
        this.food = 0;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getFood() {
        return food;
    }
    @Override
    public void buyFood() {
        this.food += 5;
    }
}
