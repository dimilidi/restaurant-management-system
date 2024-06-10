package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.shop;


import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle.Vehicle;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker.Worker;

public interface Shop {
    void make(Vehicle vehicle, Worker worker);
}
