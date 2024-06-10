package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.Climber;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ClimberRepository implements Repository<Climber>{
    private Collection<Climber> climbers = new ArrayList<>();
    @Override
    public Collection<Climber> getCollection() {
        return Collections.unmodifiableCollection(climbers);
    }

    @Override
    public void add(Climber entity) {
        climbers.add(entity);
    }

    @Override
    public boolean remove(Climber entity) {
        return climbers.remove(entity);
    }

    @Override
    public Climber byName(String name) {
        return climbers.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
