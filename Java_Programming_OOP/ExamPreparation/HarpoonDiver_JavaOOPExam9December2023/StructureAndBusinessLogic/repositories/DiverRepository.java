package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.Diver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DiverRepository implements Repository<Diver>{
    private Collection<Diver> divers = new ArrayList<>();
    @Override
    public Collection<Diver> getCollection() {
        return Collections.unmodifiableCollection(divers);
    }

    @Override
    public void add(Diver entity) {
        divers.add(entity);
    }

    @Override
    public boolean remove(Diver entity) {
        return divers.remove(entity);
    }

    @Override
    public Diver byName(String name) {
        return divers.stream()
                .filter(d -> d.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
