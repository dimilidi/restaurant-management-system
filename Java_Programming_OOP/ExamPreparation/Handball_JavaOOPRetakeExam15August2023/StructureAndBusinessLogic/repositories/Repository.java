package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Equipment;

public interface Repository {
    void add(Equipment equipment);
    boolean remove(Equipment equipment);
    Equipment findByType(String type);
}
