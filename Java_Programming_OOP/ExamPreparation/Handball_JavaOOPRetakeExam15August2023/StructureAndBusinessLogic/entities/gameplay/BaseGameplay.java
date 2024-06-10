package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.gameplay;

import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Equipment;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.common.ExceptionMessages.GAMEPLAY_NAME_NULL_OR_EMPTY;


public abstract class BaseGameplay implements Gameplay{
    private String name;
    private int capacity;
    private Collection<Equipment> equipments;
    private Collection<Team> teams;

    public BaseGameplay(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.equipments = new ArrayList<>();
        this.teams = new ArrayList<>();
    }


    @Override
    public int allProtection() {
        return equipments.stream().mapToInt(Equipment::getProtection).sum();
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void removeTeam(Team team) {
        teams.remove(team);
    }

    @Override
    public void addEquipment(Equipment equipment) {
        equipments.add(equipment);
    }

    @Override
    public void teamsInGameplay() {
        teams.forEach(Team::play);
    }

    @Override
    public Collection<Team> getTeam() {
        return teams;
    }

    @Override
    public Collection<Equipment> getEquipments() {
        return equipments;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %s\n", getName(), this.getClass().getSimpleName()) +
                String.format("Team: %s\n", teams.isEmpty() ? "none" : teams.stream().map(Team::getName).collect(Collectors.joining(" ")))  +
                String.format("Equipment: %d, Protection: %d\n", getEquipments().size(), allProtection());
    }

    public void setName(String name) {
        if(name == null ||name.trim().isEmpty()) {
            throw new NullPointerException(GAMEPLAY_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }
}
