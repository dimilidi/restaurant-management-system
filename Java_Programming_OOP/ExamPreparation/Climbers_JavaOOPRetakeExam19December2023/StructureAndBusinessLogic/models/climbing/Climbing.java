package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climbing;

import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.Climber;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain.Mountain;
import java.util.Collection;

public interface Climbing {
    void conqueringPeaks(Mountain mountain, Collection<Climber> climbers);

}
