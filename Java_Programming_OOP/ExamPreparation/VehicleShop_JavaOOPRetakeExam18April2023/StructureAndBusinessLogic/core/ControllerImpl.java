package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.core;


import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.shop.Shop;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.shop.ShopImpl;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool.Tool;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool.ToolImpl;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle.Vehicle;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle.VehicleImpl;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker.FirstShift;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker.SecondShift;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker.Worker;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.repositories.VehicleRepository;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.repositories.WorkerRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.*;

public class ControllerImpl implements Controller{
    private WorkerRepository workers = new WorkerRepository();
    private VehicleRepository vehicles = new VehicleRepository();
    private Shop shop = new ShopImpl();


    @Override
    public String addWorker(String type, String workerName) {
        Worker worker;
        switch(type) {
            case "FirstShift":
                worker = new FirstShift(workerName);
                break;
            case "SecondShift":
                worker = new SecondShift(workerName);
                break;
            default:
                throw new IllegalArgumentException(WORKER_TYPE_DOESNT_EXIST);
        }

        workers.add(worker);
        return String.format(ADDED_WORKER, type, workerName);


    }

    @Override
    public String addVehicle(String vehicleName, int strengthRequired) {
        Vehicle vehicle = new VehicleImpl(vehicleName, strengthRequired);
        vehicles.add(vehicle);

        return String.format(SUCCESSFULLY_ADDED_VEHICLE, vehicleName);
    }

    @Override
    public String addToolToWorker(String workerName, int power) {
        Worker worker = workers.findByName(workerName);

        if (worker == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        Tool tool = new ToolImpl(power);
        worker.addTool(tool);

        return String.format(SUCCESSFULLY_ADDED_TOOL_TO_WORKER, power, workerName);
    }

    @Override
    public String makingVehicle(String vehicleName) {
        Vehicle vehicle = vehicles.findByName(vehicleName);
        List<Worker> readyWorkers = workers.getWorkers().stream().filter(w -> w.getStrength() > 70).collect(Collectors.toList());

        if (readyWorkers.isEmpty()) {
            throw new IllegalArgumentException(NO_WORKER_READY);
        }

        int brokenTools = 0;

        for (Worker readyWorker : readyWorkers) {
            if(vehicle.reached()) break;

            if (readyWorker.canWork() && !vehicle.reached()) {
                shop.make(vehicle, readyWorker);
            }

            Collection<Tool> tools = readyWorker.getTools();
            List<Tool> unfitTools = tools
                    .stream()
                    .filter(Tool::isUnfit)
                    .collect(Collectors.toList());

            brokenTools += unfitTools.size();


        }

        if (vehicle.reached()) {
            return String.format(VEHICLE_DONE, vehicleName, "done") + String.format(COUNT_BROKEN_INSTRUMENTS, brokenTools);
        }

        return String.format(VEHICLE_DONE, vehicleName, "not done") + String.format(COUNT_BROKEN_INSTRUMENTS, brokenTools);
    }


    @Override
    public String statistics() {
        StringBuilder sb = new StringBuilder();

        List<Vehicle> reachedVehicles = vehicles.getWorkers().stream().filter(Vehicle::reached).collect(Collectors.toList());

        sb.append(String.format("%d vehicles are ready!",  reachedVehicles.size()));
        sb.append(System.lineSeparator());
        sb.append("Info for workers:");
        sb.append(System.lineSeparator());

        for (Worker worker : workers.getWorkers()) {
            sb.append(worker.toString());
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
