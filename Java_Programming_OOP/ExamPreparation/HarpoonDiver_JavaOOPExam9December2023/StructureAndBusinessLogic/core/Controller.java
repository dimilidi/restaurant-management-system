package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core;

public interface Controller {
    String addDiver(String kind, String diverName);

    String addDivingSite(String siteName, String... seaCreatures);

    String removeDiver(String diverName);

    String startDiving(String siteName);

    String getStatistics();

}
