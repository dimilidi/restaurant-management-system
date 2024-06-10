package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces;

public interface BoothRepository<T> extends Repository<T> {
    T getByNumber(int number);
}
