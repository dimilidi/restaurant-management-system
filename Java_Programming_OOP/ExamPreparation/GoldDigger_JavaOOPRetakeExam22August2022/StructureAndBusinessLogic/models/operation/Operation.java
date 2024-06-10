package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.operation;


import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Discoverer;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.spot.Spot;

import java.util.Collection;

public interface Operation {
    void startOperation(Spot spot, Collection<Discoverer> discoverers);

}
