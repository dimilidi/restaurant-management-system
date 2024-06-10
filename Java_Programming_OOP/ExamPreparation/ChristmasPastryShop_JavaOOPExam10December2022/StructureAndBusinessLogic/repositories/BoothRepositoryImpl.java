package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories;


import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.interfaces.Booth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.BoothRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BoothRepositoryImpl implements BoothRepository<Booth> {
    private Collection<Booth> models = new ArrayList<>();
    @Override
    public Booth getByNumber(int boothNumber) {
       return models.stream()
               .filter(m -> m.getBoothNumber() == boothNumber)
               .findFirst()
               .orElse(null);
    }
    @Override
    public Collection<Booth> getAll() {
        return Collections.unmodifiableCollection(models);
    }
    @Override
    public void add(Booth booth) {
        models.add(booth);
    }
}
