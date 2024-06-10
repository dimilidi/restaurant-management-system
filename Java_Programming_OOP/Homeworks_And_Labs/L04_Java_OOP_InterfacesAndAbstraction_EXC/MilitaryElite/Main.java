package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations.*;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Soldier;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Soldier> soldiersList = new ArrayList<>();
        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String[] commandParts = command.split("\\s+");
            String Type = commandParts[0];
            switch (Type) {
                case "Private" -> {
                    PrivateImpl soldier = new PrivateImpl(Integer.parseInt(commandParts[1]), commandParts[2],
                            commandParts[3], Double.parseDouble(commandParts[4]));
                    soldiersList.add(soldier);
                }
                case "LieutenantGeneral" -> {
                    LieutenantGeneralImpl soldier = new LieutenantGeneralImpl(Integer.parseInt(commandParts[1]), commandParts[2],
                            commandParts[3], Double.parseDouble(commandParts[4]));
                    addPrivate(soldiersList, soldier, commandParts);
                    soldiersList.add(soldier);
                }
                case "Engineer" -> {
                    try {
                        EngineerImpl engineer = new EngineerImpl(Integer.parseInt(commandParts[1]), commandParts[2],
                                commandParts[3], Double.parseDouble(commandParts[4]), commandParts[5]);
                        addRepair(engineer, commandParts);
                        soldiersList.add(engineer);
                    } catch (IllegalArgumentException e) {

                    }
                }
                case "Commando" -> {
                    try {
                        CommandoImpl commando = new CommandoImpl(Integer.parseInt(commandParts[1]), commandParts[2], commandParts[3], Double.parseDouble(commandParts[4]), commandParts[5]);
                        addMission(commando, commandParts);
                        soldiersList.add(commando);
                    } catch (IllegalArgumentException e) {

                    }
                }
                case "Spy" -> {
                    SpyImpl spy = new SpyImpl(Integer.parseInt(commandParts[1]), commandParts[2], commandParts[3], commandParts[4]);
                    soldiersList.add(spy);
                }
            }
            command = scanner.nextLine();
        }
        soldiersList.forEach(System.out::println);
    }

    private static void addMission(CommandoImpl commando, String[] commandParts) {
        for (int i = 6; i < commandParts.length; i += 2) {
            String CodeName = commandParts[i];
            String State = commandParts[i + 1];
            try {
                MissionImpl mission = new MissionImpl(CodeName, State);
                commando.addMission(mission);
            } catch (IllegalArgumentException e) {

            }
        }
    }

    private static void addRepair(EngineerImpl engineer, String[] commandParts) {
        for (int i = 6; i < commandParts.length; i += 2) {
            String partName = commandParts[i];
            int workHours = Integer.parseInt(commandParts[i + 1]);
            RepairImpl repair = new RepairImpl(partName, workHours);
            engineer.addRepair(repair);
        }
    }

    private static void addPrivate(List<Soldier> soldiersList, LieutenantGeneralImpl lieutenantGeneral, String[] commandParts) {
        for (int i = 5; i < commandParts.length; i++) {
            for (Soldier soldier : soldiersList) {
                if (soldier.getId() == Integer.parseInt(commandParts[i])) {
                    lieutenantGeneral.addPrivate(soldier);
                }
            }
        }
    }
}
