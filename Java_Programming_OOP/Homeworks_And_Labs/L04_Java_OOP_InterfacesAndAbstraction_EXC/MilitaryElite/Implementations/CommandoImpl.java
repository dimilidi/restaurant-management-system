package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Commando;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Mission;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {
    private List<Mission> missionsList;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        this.missionsList = new ArrayList<>();
    }

    @Override
    public void addMission(Mission mission) {
        missionsList.add(mission);
    }

    @Override
    public Collection<Mission> getMissions() {
        return Collections.unmodifiableList(missionsList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s %s Id: %d Salary: %.2f", this.getFirstName(), this.getLastName(), this.getId(), this.getSalary())).append(System.lineSeparator());
        stringBuilder.append("Corps: ").append(corp).append(System.lineSeparator());
        stringBuilder.append("Missions:").append(System.lineSeparator());
        for (Mission mission : missionsList) {
            stringBuilder.append(" ").append(mission.toString()).append(System.lineSeparator());
        }
        return stringBuilder.toString().trim();
    }
}