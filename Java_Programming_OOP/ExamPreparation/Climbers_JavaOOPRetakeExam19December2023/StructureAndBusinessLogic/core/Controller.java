package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core;

public interface Controller {
    String addClimber(String type, String climberName);

    String addMountain(String mountainName, String... peaks);

    String removeClimber(String climberName);

    String startClimbing(String mountainName);

    String getStatistics();

}
