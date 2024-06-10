package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.commands;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Executable;

public abstract class Command implements Executable {
    private String[] data;

    public Command(String[] data) {
        this.data = data;
    }

    protected String[] getData() {
        return data;
    }

}