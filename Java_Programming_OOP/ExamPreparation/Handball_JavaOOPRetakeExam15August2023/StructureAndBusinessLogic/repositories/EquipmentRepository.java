package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.repositories;

import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Equipment;

import java.util.ArrayList;
import java.util.Collection;

public class EquipmentRepository implements Repository{
    private Collection<Equipment> equipments = new ArrayList<>();
    @Override
    public void add(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public boolean remove(Equipment equipment) {
        return equipments.remove(equipment);
    }

    @Override
    public Equipment findByType(String type) {
        return equipments.stream().filter(e -> e.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
