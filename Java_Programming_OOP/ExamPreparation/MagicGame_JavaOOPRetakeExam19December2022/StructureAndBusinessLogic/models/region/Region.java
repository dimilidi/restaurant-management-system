package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.region;


import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians.Magician;

import java.util.Collection;

public interface Region {
    String start(Collection<Magician> magicians);

}
