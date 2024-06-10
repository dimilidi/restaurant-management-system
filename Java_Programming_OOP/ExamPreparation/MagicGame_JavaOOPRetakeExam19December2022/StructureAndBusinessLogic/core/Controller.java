package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.core;

public interface Controller {
    String addMagic(String type, String name, int bulletsCount);

    String addMagician(String type, String username, int health, int protection, String magicName);

    String startGame();

    String report();
}

