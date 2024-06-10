package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core.commands;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {
    public Retire(String[] data, Repository repository, UnitFactory unitFactory) {
        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];

        String res = unitType + " retired!";

        try {
            this.getRepository().removeUnit(unitType);
        } catch (RuntimeException  | ExecutionControl.NotImplementedException e) {
            return e.getMessage();
        }

        return res;
    }
}

