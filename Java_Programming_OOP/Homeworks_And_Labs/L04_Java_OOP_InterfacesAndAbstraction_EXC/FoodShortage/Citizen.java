package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.FoodShortage;

public class Citizen implements Person, Birthable, Identifiable, Buyer {
    private String name;
    private int age;
    private String id;
    private String birthDate;
    private int food;

    public Citizen(String name, int age, String id, String birthDate) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthDate = birthDate;
        this.food = 0;
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
    public String getId() {
        return id;
    }
    @Override
    public String getBirthDate() {
        return birthDate;
    }
    @Override
    public int getFood() {
        return food;
    }
    @Override
    public void buyFood() {
        this.food += 10;
    }
}

