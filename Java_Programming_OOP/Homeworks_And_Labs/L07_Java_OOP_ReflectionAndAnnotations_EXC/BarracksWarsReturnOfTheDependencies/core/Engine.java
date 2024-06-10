package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.core;

import Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.interfaces.CommandInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Engine implements Runnable {
	private CommandInterpreter commandInterpreter;

	public Engine(CommandInterpreter commandInterpreter) {
		this.commandInterpreter = commandInterpreter;
	}

	@Override
	public void run() {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in));
		while (true) {
			try {
				String input = reader.readLine();
				String[] data = input.split("\\s+");
				String result = commandInterpreter.interpretCommand(data).execute();
				if (result.equals("fight")) {
					break;
				}
				System.out.println(result);
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}