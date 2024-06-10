package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;

public interface Magician {
    String getUsername();

    int getHealth();

    int getProtection();

    Magic getMagic();

    boolean isAlive();

    void takeDamage(int points);
}
