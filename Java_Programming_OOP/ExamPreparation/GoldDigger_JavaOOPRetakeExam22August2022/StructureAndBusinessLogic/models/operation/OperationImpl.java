package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.operation;

import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Discoverer;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.spot.Spot;

import java.util.Collection;
import java.util.Iterator;

public class OperationImpl implements Operation {
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Iterator<String> iterator = spot.getExhibits().iterator();

        for (Discoverer discoverer : discoverers) {

            while(iterator.hasNext()) {
                if(!discoverer.canDig()) {
                    break;
                }
                discoverer.dig();

                String item = iterator.next();
                discoverer.getMuseum().getExhibits().add(item);
                iterator.remove();
            }
        }

    }
}
