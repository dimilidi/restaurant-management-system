package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.core;

public interface Controller {
    String addDiscoverer(String kind, String discovererName);

    String addSpot(String spotName, String... exhibits);

    String excludeDiscoverer(String discovererName);

    String inspectSpot(String spotName);

    String getStatistics();
}
