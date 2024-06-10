package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;

public class Wizard extends MagicianImpl{

    public Wizard(String username, int health, int protection, Magic magic) {
        super(username, health, protection, magic);
    }
}
