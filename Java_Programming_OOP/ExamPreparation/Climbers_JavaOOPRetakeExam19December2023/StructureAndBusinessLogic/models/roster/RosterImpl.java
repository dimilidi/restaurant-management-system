package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.roster;

import java.util.ArrayList;
import java.util.Collection;

public class RosterImpl implements Roster{
    private Collection<String> peaks = new ArrayList<>();
    @Override
    public Collection<String> getPeaks() {
        return peaks;
    }
}
