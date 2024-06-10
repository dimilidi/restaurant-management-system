package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.WildFarm;
public abstract class Food {
    private Integer quantity;

    protected Food(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
