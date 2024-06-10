package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.repositories;

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getCollection();

    void add(T entity);

    boolean remove(T entity);

    T byName(String name);
}
