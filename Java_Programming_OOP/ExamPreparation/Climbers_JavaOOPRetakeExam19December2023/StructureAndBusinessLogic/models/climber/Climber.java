package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.roster.Roster;

public interface Climber {
    String getName();

    double getStrength();

    boolean canClimb();

    Roster getRoster();


    void climb();
}
