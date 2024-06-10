package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver;


import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.seaCatch.BaseSeaCatch;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.seaCatch.SeaCatch;

import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ExceptionMessages.DIVER_NAME_NULL_OR_EMPTY;
import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ExceptionMessages.DIVER_OXYGEN_LESS_THAN_ZERO;

public abstract class BaseDiver implements Diver{
    private String name;
    private double oxygen;
    private SeaCatch seaCatch;

    protected BaseDiver(String name, double oxygen) {
        setName(name);
        setOxygen(oxygen);
        this.seaCatch = new BaseSeaCatch();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getOxygen() {
        return oxygen;
    }

    @Override
    public boolean canDive() {
        return oxygen > 0;
    }

    @Override
    public SeaCatch getSeaCatch() {
        return seaCatch;
    }

    @Override
    public void shoot() {
        setOxygen(Math.max(0, oxygen - 30));
    }

    @Override
    public String toString() {
        String seaCreatures = String.join(FINAL_DIVER_CATCH_DELIMITER, seaCatch.getSeaCreatures());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_DIVER_NAME, name));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_DIVER_OXYGEN, oxygen));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_DIVER_CATCH, seaCreatures.isEmpty() ? "None" : seaCreatures));

        return sb.toString().trim();
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(DIVER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setOxygen(double oxygen) {
        if(oxygen < 0) {
            throw new IllegalArgumentException(DIVER_OXYGEN_LESS_THAN_ZERO);
        }
        this.oxygen = oxygen;
    }
}
