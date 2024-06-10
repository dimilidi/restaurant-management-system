package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.divingSite;


import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ExceptionMessages.SITE_NAME_NULL_OR_EMPTY;

public class DivingSiteImpl implements DivingSite{
    private String name;
    private Collection<String> seaCreatures;

    public DivingSiteImpl(String name) {
        if (name == null || name.trim().isEmpty()){
            throw new NullPointerException(SITE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;

        this.seaCreatures = new ArrayList<>();
    }


    @Override
    public Collection<String> getSeaCreatures() {
        return seaCreatures;
    }

    @Override
    public String getName() {
        return name;
    }
}
