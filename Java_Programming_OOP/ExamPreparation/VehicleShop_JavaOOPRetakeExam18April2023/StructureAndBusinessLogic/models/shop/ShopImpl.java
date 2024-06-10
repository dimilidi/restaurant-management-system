package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.shop;

import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool.Tool;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle.Vehicle;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker.Worker;

public class ShopImpl implements Shop {
    @Override
    public void make(Vehicle vehicle, Worker worker) {
        if (worker.canWork()) {
            for (Tool tool : worker.getTools()) {
                while (!tool.isUnfit() && worker.canWork()) {
                    vehicle.making();
                    worker.working();
                    tool.decreasesPower();

                    if (vehicle.reached()) {
                        return;
                    }
                }
            }
        }
    }

}
