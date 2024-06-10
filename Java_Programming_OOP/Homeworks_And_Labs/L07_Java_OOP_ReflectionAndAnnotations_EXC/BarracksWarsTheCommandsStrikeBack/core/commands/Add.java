package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core.commands;


import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Unit;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command {
    public Add(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = getData()[1];
        Unit unitToAdd = null;
        try {
            unitToAdd = getUnitFactory().createUnit(unitType);
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
        getRepository().addUnit(unitToAdd);
        return unitType + " added!";
    }
}
