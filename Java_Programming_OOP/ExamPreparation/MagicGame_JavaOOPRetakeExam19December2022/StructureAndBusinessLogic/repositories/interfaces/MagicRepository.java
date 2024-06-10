package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.repositories.interfaces;


import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;

import java.util.Collection;

public interface MagicRepository<T> {
    Collection<T> getData();

    void addMagic(Magic model);

    boolean removeMagic(Magic model);

    T findByName(String name);
}
