package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.repositories;

import java.util.Collection;

public interface Repository<T> {
    Collection<T> getWorkers();

    void add(T model);

    boolean remove(T model);

    T findByName(String name);
}
