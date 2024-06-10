package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer;


import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.museum.Museum;

public interface Discoverer {
    String getName();

    double getEnergy();

    boolean canDig();

    Museum getMuseum();

    void dig();
}
