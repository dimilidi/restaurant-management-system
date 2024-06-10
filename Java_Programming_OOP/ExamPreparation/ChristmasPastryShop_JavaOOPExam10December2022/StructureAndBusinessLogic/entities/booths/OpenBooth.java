package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths;

public class OpenBooth extends BaseBooth{
    double PRICE_PER_PERSON = 2.50;
    public OpenBooth(int boothNumber, int capacity, double pricePerPerson) {
        super(boothNumber, capacity, 2.50);
    }
}
