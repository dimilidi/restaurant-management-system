package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core.commands.Command;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.Repository;
import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Engine implements Runnable {
	private static final String COMMANDS_PACKAGE_NAME =
			"Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsTheCommandsStrikeBack.core.commands.";

	private Repository repository;
	private UnitFactory unitFactory;

	public Engine(Repository repository, UnitFactory unitFactory) {
		this.repository = repository;
		this.unitFactory = unitFactory;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String commandName = data[0];
				String result = interpretCommand(data, commandName);
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException | ExecutionControl.NotImplementedException e) {
				e.printStackTrace();
			}
		}
	}

	private String interpretCommand(String[] data, String commandName) throws ExecutionControl.NotImplementedException {
		String result = null;

		Command command = null;
		String className = Character.toUpperCase(commandName.charAt(0)) + commandName.substring(1);

		result = buildCommand(data, className);

		return result;
	}

	private String buildCommand(String[] data, String className) {
		String result = "";
		try {
			Class commandClass = Class.forName(COMMANDS_PACKAGE_NAME + className);
			Constructor<Command> commandConstructor = commandClass.getConstructor(String[].class, Repository.class, UnitFactory.class);

			Command commandObject = commandConstructor.newInstance(data, this.repository, this.unitFactory);
			result = commandObject.execute();

		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
				 InvocationTargetException e) {
			e.printStackTrace();
		}
		return result;
	}

}

