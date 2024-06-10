package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.Supplement;

public interface Repository {

    void addSupplement(Supplement supplement);

    boolean removeSupplement(Supplement supplement);

    Supplement findFirst(String type);
}
