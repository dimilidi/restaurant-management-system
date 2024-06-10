package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic;

import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.core.Engine;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.core.EngineImpl;

public class Main {
    public static void main(String[] args) {

        Engine engine = new EngineImpl();
        engine.run();
    }
}
