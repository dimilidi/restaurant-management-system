package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic;

import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core.Controller;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core.ControllerImpl;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core.Engine;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core.EngineImpl;

public class Main {

    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
