package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diving;

import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.Diver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.divingSite.DivingSite;

import java.util.Collection;

public interface Diving {

    void searching(DivingSite divingSite, Collection<Diver> divers);
}
