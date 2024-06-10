package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climbing;

import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.Climber;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain.Mountain;

import java.util.Collection;
import java.util.Iterator;

public class ClimbingImpl implements Climbing{
    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        for (Climber climber : climbers) {
            Iterator<String> iterator = mountain.getPeaksList().iterator();
            while (iterator.hasNext()) {
                String peak = iterator.next();
                if (climber.canClimb()) {
                    climber.climb();
                    climber.getRoster().getPeaks().add(peak);
                    iterator.remove();
                } else {
                    break;
                }
            }
        }
    }

}
