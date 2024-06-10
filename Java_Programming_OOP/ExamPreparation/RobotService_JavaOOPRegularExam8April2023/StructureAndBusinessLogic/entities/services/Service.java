package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services;


import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot.Robot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.Supplement;

import java.util.Collection;

public interface Service {
    String getName();

    void setName(String name);

    Collection<Robot> getRobots();

    Collection<Supplement> getSupplements();

    void addRobot(Robot robot);

    void removeRobot(Robot robot);

    void addSupplement(Supplement supplement);

    void feeding();

    int sumHardness();

    String getStatistics();
}
