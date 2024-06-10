package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver;

import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.seaCatch.SeaCatch;

public interface Diver {
    String getName();

    double getOxygen();

    boolean canDive();

    SeaCatch getSeaCatch();

    void shoot();
}
