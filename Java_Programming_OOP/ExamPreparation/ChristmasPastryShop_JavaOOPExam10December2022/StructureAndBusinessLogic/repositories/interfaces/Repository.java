package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces;

import java.util.Collection;

public interface Repository<T> {

    Collection<T> getAll();

    void add(T t);

}
