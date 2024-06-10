package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths;

public class PrivateBooth extends BaseBooth{
    double PRICE_PER_PERSON = 3.50;
    public PrivateBooth(int boothNumber, int capacity, double pricePerPerson) {
        super(boothNumber, capacity, 3.50);
    }
}
