package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.commands;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Inject;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Repository;
public class Retire extends Command {
    @Inject
    private Repository repository;
    public Retire(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        this.repository.removeUnit(unitType);
        return unitType + " retired!";
    }
}
