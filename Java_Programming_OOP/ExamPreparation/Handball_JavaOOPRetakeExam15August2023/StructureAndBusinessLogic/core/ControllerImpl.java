package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.core;


import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.ElbowPad;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Equipment;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Kneepad;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.gameplay.Gameplay;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.gameplay.Indoor;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.gameplay.Outdoor;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team.Bulgaria;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team.Germany;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team.Team;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.repositories.EquipmentRepository;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private Repository equipment;
    private Collection<Gameplay> gameplays;
    public ControllerImpl() {
        this.equipment = new EquipmentRepository();
        this.gameplays = new ArrayList<>();
    }

    @Override
    public String addGameplay(String gameplayType, String gameplayName) {
        Gameplay gameplay;
        switch(gameplayType) {
            case "Outdoor":
                gameplay = new Outdoor(gameplayName);
                break;
            case "Indoor":
                gameplay = new Indoor(gameplayName);
                break;
            default:
                throw new NullPointerException(INVALID_GAMEPLAY_TYPE);
        }

        gameplays.add(gameplay);
        return String.format(SUCCESSFULLY_ADDED_GAMEPLAY_TYPE, gameplayType);
    }


    @Override
    public String addEquipment(String equipmentType) {
        Equipment equipmentItem;
        switch(equipmentType) {
            case "Kneepad":
                equipmentItem = new Kneepad();
                break;
            case "ElbowPad":
                equipmentItem = new ElbowPad();
                break;
            default:
                throw new IllegalArgumentException(INVALID_EQUIPMENT_TYPE);
        }

        equipment.add(equipmentItem);
        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_TYPE, equipmentType);
    }


    @Override
    public String equipmentRequirement(String gameplayName, String equipmentType) {
        Equipment desiredEquipment = equipment.findByType(equipmentType);

        if (desiredEquipment == null) {
            throw new IllegalArgumentException(String.format(NO_EQUIPMENT_FOUND, equipmentType));
        }

        equipment.remove(desiredEquipment);
        findGameplayByName(gameplays, gameplayName).addEquipment(desiredEquipment);

        return String.format(SUCCESSFULLY_ADDED_EQUIPMENT_IN_GAMEPLAY, equipmentType, gameplayName);
    }

    @Override
    public String addTeam(String gameplayName, String teamType, String teamName, String country, int advantage) {
        Gameplay gameplay = findGameplayByName(gameplays, gameplayName);
        Team team;
        switch(teamType) {
            case "Bulgaria":
                team = new Bulgaria(teamName, country, advantage);
                break;
            case "Germany":
                team = new Germany(teamName, country, advantage);
                break;
            default:
                throw new IllegalArgumentException(INVALID_TEAM_TYPE);
        }

        if(!isValidTerrain(teamType, gameplay)) {
            return GAMEPLAY_NOT_SUITABLE;
        }
        gameplay.addTeam(team);
        return String.format(SUCCESSFULLY_ADDED_TEAM_IN_GAMEPLAY, teamType, gameplayName);
    }

    @Override
    public String playInGameplay(String gameplayName) {
        Gameplay gameplay = findGameplayByName(gameplays, gameplayName);
        gameplay.teamsInGameplay();
        int count = gameplay.getTeam().size();

        return String.format(TEAMS_PLAYED, count);
    }

    @Override
    public String percentAdvantage(String gameplayName) {
        Gameplay gameplay = findGameplayByName(gameplays, gameplayName);
        int advantageSum = gameplay.getTeam().stream().mapToInt(Team::getAdvantage).sum();
        return String.format(ADVANTAGE_GAMEPLAY, gameplayName, advantageSum);
    }


    @Override
    public String getStatistics() {
        return gameplays.stream().map(Gameplay::toString).collect(Collectors.joining("\n"));
    }


    private static Gameplay findGameplayByName(Collection<Gameplay> gameplays, String name) {
        return gameplays.stream().filter(g -> g.getName().equals(name)).findFirst().orElse(null);
    }

    private static boolean isValidTerrain(String teamType, Gameplay gameplay) {
        if (gameplay instanceof Outdoor && teamType.equals("Bulgaria")) {
            return true;
        }

        if (gameplay instanceof Indoor && teamType.equals("Germany")) {
            return true;
        }
        return false;
    }

}
