package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.interfaces;

public interface Booth {
    int getBoothNumber();

    int getCapacity();

    boolean isReserved();

    double getPrice();

    void reserve(int numberOfPeople);

    double getBill();

    void clear();
}
