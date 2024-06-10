package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain.Mountain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MountainRepository implements Repository<Mountain> {

    private Collection<Mountain> mountains = new ArrayList<>();
    @Override
    public Collection<Mountain> getCollection() {
        return Collections.unmodifiableCollection(mountains);
    }

    @Override
    public void add(Mountain entity) {
        mountains.add(entity);
    }

    @Override
    public boolean remove(Mountain entity) {
        return mountains.remove(entity);
    }

    @Override
    public Mountain byName(String name) {
        return mountains.stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
    }
}
