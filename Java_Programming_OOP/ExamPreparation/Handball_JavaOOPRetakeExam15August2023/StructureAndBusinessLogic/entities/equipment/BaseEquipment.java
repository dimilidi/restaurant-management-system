package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment;

public abstract class BaseEquipment implements Equipment{
    private int protection;
    private double price;

    protected BaseEquipment(int protection, double price) {
        this.protection = protection;
        this.price = price;
    }

    @Override
    public int getProtection() {
        return protection;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
