package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic;


import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.core.Engine;
import ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImpl();
        engine.run();
    }
}
