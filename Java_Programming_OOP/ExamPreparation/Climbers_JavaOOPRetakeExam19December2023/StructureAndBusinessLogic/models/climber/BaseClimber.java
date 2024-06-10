package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.roster.Roster;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.roster.RosterImpl;

import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ExceptionMessages.CLIMBER_NAME_NULL_OR_EMPTY;
import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ExceptionMessages.CLIMBER_STRENGTH_LESS_THAN_ZERO;

public abstract class BaseClimber implements Climber{

    private String name;
    private double strength;
    private Roster roster;

    protected BaseClimber(String name, double strength) {
        setName(name);
        setStrength(strength);
        this.roster = new RosterImpl();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getStrength() {
        return strength;
    }

    @Override
    public boolean canClimb() {
        return strength > 0;
    }

    @Override
    public Roster getRoster() {
        return roster;
    }


    @Override
    public String toString() {
        String conqueredPeaks = String.join(FINAL_CLIMBER_FINDINGS_DELIMITER, roster.getPeaks());

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_CLIMBER_NAME, name));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_CLIMBER_STRENGTH, strength));
        sb.append(System.lineSeparator());
        sb.append(String.format(FINAL_CLIMBER_PEAKS, conqueredPeaks.isEmpty() ? "None" : conqueredPeaks));

        return sb.toString().trim();
    }


    protected void decreaseStrength(int amount) {
        setStrength(Math.max(0, strength - amount));
    }

    private void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(CLIMBER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setStrength(double strength) {
        if(strength < 0) {
            throw new IllegalArgumentException(CLIMBER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }
}
