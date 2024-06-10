package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker;

import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool.Tool;
import java.util.Collection;

public interface Worker {
    void working();

    void addTool(Tool tool);

    boolean canWork();

    String getName();

    int getStrength();

    Collection<Tool> getTools();
}
