package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic;


import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.core.Engine;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
