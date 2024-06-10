package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.commands;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Inject;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Unit;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command{
    private String[] data;
    @Inject
    private Repository repository;
    @Inject
    private UnitFactory unitFactory;
    public Add(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = this.unitFactory.createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        this.repository.addUnit(unitToAdd);
        return unitType + " added!";
    }
}