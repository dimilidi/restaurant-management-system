package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic;

import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core.Controller;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core.ControllerImpl;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core.Engine;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core.EngineImpl;


public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
