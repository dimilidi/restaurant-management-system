package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.gameplay;


import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.equipment.Equipment;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team.Team;

import java.util.Collection;

public interface Gameplay {
    int allProtection();

    void addTeam(Team team);

    void removeTeam(Team team);

    void addEquipment(Equipment equipment);

    void teamsInGameplay();

    Collection<Team> getTeam();

    Collection<Equipment> getEquipments();

    String getName();


}
