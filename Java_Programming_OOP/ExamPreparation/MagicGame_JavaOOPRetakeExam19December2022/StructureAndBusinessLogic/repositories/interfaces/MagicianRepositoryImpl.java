package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.repositories.interfaces;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians.Magician;

import java.util.*;

import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.ExceptionMessages.INVALID_MAGICIAN_REPOSITORY;

public class MagicianRepositoryImpl implements MagicianRepository<Magician>{

    private Map<String, Magician> data = new LinkedHashMap<>();

    @Override
    public Collection<Magician> getData() {
        return Collections.unmodifiableCollection(data.values());
    }

    @Override
    public void addMagician(Magician model) {
        if(model == null) {
            throw new NullPointerException(INVALID_MAGICIAN_REPOSITORY);
        }

        data.put(model.getUsername(), model);
    }

    @Override
    public boolean removeMagician(Magician model) {
        return data.remove(model.getUsername())  != null;
    }

    @Override
    public Magician findByUsername(String name) {
        return data.get(name);
    }
}
