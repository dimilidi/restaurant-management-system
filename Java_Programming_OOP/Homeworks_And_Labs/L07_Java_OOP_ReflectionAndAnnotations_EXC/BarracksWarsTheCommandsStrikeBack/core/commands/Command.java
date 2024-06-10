package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core.commands;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Executable;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;

public abstract class Command implements Executable {
    private String[] data;
    private Repository repository;
    private UnitFactory unitFactory;

    protected Command(String[] data, Repository repository, UnitFactory unitFactory) {
        this.data = data;
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    protected String[] getData() {
        return this.data;
    }

    protected Repository getRepository() {
        return this.repository;
    }

    protected UnitFactory getUnitFactory() {
        return this.unitFactory;
    }
}