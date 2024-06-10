package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ExceptionMessages.MOUNTAIN_NAME_NULL_OR_EMPTY;

public class MountainImpl implements Mountain{
    private String name;
    private Collection<String> peaksList;

    public MountainImpl(String name) {
        setName(name);
        this.peaksList = new ArrayList<>();
    }

    @Override
    public Collection<String> getPeaksList() {
        return peaksList;
    }

    @Override
    public String getName() {
        return name;
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(MOUNTAIN_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
}
