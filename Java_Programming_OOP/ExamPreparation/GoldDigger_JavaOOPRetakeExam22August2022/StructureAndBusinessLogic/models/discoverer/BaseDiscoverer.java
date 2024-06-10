package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer;


import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.museum.BaseMuseum;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.museum.Museum;

import static ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ExceptionMessages.DISCOVERER_ENERGY_LESS_THAN_ZERO;
import static ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ExceptionMessages.DISCOVERER_NAME_NULL_OR_EMPTY;

public abstract class BaseDiscoverer implements Discoverer {
   private String name;
   private double energy;
   private Museum museum;

    protected BaseDiscoverer(String name, double energy) {
        this.setName(name);
        this.setEnergy(energy);
        this.museum = new BaseMuseum();
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public Museum getMuseum() {
        return this.museum;
    }
    @Override
    public boolean canDig() {
        return this.energy > 0;
    }

    @Override
    public void dig() {
        this.energy -= 15;

        if (this.energy < 0) {
            this.energy = 0;
        }

    }

    private void setName(String name) {
        if(name == null | name.trim().isEmpty()) {
            throw new NullPointerException(DISCOVERER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }
    private void setEnergy(double energy) {
        if(energy < 0) {
            throw new IllegalArgumentException(DISCOVERER_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(String.format(FINAL_DISCOVERER_NAME, this.name));
        out.append(System.lineSeparator());
        out.append(String.format(FINAL_DISCOVERER_ENERGY, this.energy));
        out.append(System.lineSeparator());
        if(this.museum.getExhibits().isEmpty()){
            out.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS, "None"));
        }else{
            out.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS,
                    String.join(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER, this.museum.getExhibits())));
        }

        return out.toString();

    }
}
