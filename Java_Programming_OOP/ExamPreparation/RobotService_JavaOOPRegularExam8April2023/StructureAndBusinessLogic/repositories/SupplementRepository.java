package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.repositories;

import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;

public class SupplementRepository implements Repository{
    private Collection<Supplement> supplements = new ArrayList<>();
    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public boolean removeSupplement(Supplement supplement) {
        return supplements.remove(supplement);
    }

    @Override
    public Supplement findFirst(String type) {
        return supplements.stream().filter(s -> s.getClass().getSimpleName().equals(type)).findFirst().orElse(null);
    }
}
