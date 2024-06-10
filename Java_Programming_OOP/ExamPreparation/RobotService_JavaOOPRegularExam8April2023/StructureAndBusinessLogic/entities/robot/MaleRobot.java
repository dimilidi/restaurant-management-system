package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot;

public class MaleRobot extends BaseRobot{
    public MaleRobot(String name, String kind, double price) {
        super(name, kind, 9, price);
    }
    @Override
    public void eating() {
        super.increaseKilograms(3);

    }
}
