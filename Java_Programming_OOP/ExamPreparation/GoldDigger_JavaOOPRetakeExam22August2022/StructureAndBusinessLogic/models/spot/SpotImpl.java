package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.spot;

import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class SpotImpl implements Spot {
    private String name;
    private List<String> exhibits;

    public SpotImpl(String name) {
        this.setName(name);
        this.exhibits = new ArrayList<>();
    }

    private void setName(String name) {
        if(name == null | name.trim().isEmpty()) {
            throw new NullPointerException(ExceptionMessages.SPOT_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    @Override
    public Collection<String> getExhibits() {
        return this.exhibits;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
