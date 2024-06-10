package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services;


public class MainService extends BaseService {

    private static final int CAPACITY = 30;

    public MainService(String name) {
        super(name, CAPACITY);
    }
}