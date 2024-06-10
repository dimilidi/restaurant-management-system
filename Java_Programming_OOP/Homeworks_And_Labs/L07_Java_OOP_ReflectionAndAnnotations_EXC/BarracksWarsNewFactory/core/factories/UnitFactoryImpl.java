package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.core.factories;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.interfaces.Unit;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

	private static final String UNITS_PACKAGE_NAME =
			"Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.barracksWars.models.units.";

	@Override
	public Unit createUnit(String unitType) {
		// TODO: implement for problem 3
		try {
			Class clazz = Class.forName(UNITS_PACKAGE_NAME + unitType);
			Constructor<Unit> constructor =  clazz.getDeclaredConstructor();
			return constructor.newInstance();
		} catch (ClassNotFoundException  | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
			System.out.println(e.getMessage());
		}

		return null;

	}
}
