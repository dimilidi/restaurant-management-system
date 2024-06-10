package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies;


import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.CommandInterpreterImpl;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.Engine;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core.factories.UnitFactoryImpl;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.data.UnitRepository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(new CommandInterpreterImpl(repository, unitFactory));
        engine.run();
    }
}
