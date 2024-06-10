package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.core.Engine;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.core.factories.UnitFactoryImpl;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.data.UnitRepository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}
