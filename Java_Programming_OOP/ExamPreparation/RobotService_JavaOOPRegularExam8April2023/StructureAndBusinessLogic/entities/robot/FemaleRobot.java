package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot;

public class FemaleRobot extends BaseRobot{
    public FemaleRobot(String name, String kind, double price) {
        super(name, kind, 7, price);
    }

    @Override
    public void eating() {
        super.increaseKilograms(1);
    }


}
