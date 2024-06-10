package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.core;


import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot.FemaleRobot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot.MaleRobot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot.Robot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services.MainService;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services.SecondaryService;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services.Service;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.MetalArmor;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.PlasticArmor;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.Supplement;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.repositories.SupplementRepository;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.common.ExceptionMessages.*;


public class ControllerImpl implements Controller {
    private SupplementRepository supplements;
    private Collection<Service> services;

    public ControllerImpl() {
        this.supplements = new SupplementRepository();
        this.services = new ArrayList<>();
    }

    @Override
    public String addService(String type, String name) {
        Service service;
        switch (type) {
            case "SecondaryService":
                service = new SecondaryService(name);
                break;
            case "MainService":
                service = new MainService(name);
                break;
            default:
                throw new NullPointerException(INVALID_SERVICE_TYPE);
        }

        services.add(service);
        return String.format(SUCCESSFULLY_ADDED_SERVICE_TYPE, type);
    }

    @Override
    public String addSupplement(String type) {
        Supplement supplement;
        switch (type) {
            case "PlasticArmor":
                supplement = new PlasticArmor();
                break;
            case "MetalArmor":
                supplement = new MetalArmor();
                break;
            default:
                throw new NullPointerException(INVALID_SUPPLEMENT_TYPE);
        }

        supplements.addSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_TYPE, type);
    }

    @Override
    public String supplementForService(String serviceName, String supplementType) {
        Supplement supplement = supplements.findFirst(supplementType);

        if (supplement == null) {
            throw new IllegalArgumentException(String.format(NO_SUPPLEMENT_FOUND, supplementType));
        }

        Service service = getService(serviceName);
        service.addSupplement(supplement);
        supplements.removeSupplement(supplement);
        return String.format(SUCCESSFULLY_ADDED_SUPPLEMENT_IN_SERVICE, supplementType, serviceName);
    }

    @Override
    public String addRobot(String serviceName, String robotType, String robotName, String robotKind, double price) {
        Service service = getService(serviceName);

        Robot robot;
        switch (robotType) {
            case "MaleRobot":
                robot = new MaleRobot(robotName, robotKind, price);
                break;
            case "FemaleRobot":
                robot = new FemaleRobot(robotName, robotKind, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_ROBOT_TYPE);
        }

        if (!isValidServiceTypeForRobot(service, robotType)) {
            return String.format(UNSUITABLE_SERVICE);
        } else {
            service.addRobot(robot);
            return String.format(SUCCESSFULLY_ADDED_ROBOT_IN_SERVICE, robotType, serviceName);
        }
    }


    @Override
    public String feedingRobot(String serviceName) {
        Service service = getService(serviceName);
        service.feeding();

        int robotsFed = service.getRobots().size();

        return String.format(FEEDING_ROBOT, robotsFed);
    }

    @Override
    public String sumOfAll(String serviceName) {
        Service service = getService(serviceName);
        double robotsPrice = service.getRobots().stream().mapToDouble(Robot::getPrice).sum();
        double supplementsPrice = service.getSupplements().stream().mapToDouble(Supplement::getPrice).sum();
        double totalPrice = robotsPrice + supplementsPrice;

        return String.format(VALUE_SERVICE, serviceName, totalPrice);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        services.forEach(s -> sb.append(String.format("%s%n", s.getStatistics())));

        return sb.toString().trim();
    }

    private static boolean isValidServiceTypeForRobot(Service service, String robotType) {
        if (robotType.equals("MaleRobot")
                && service.getClass().getSimpleName().equals("MainService")) {
            return true;
        }
        if (robotType.equals("FemaleRobot")
                && service.getClass().getSimpleName().equals("SecondaryService")
        ) {
            return true;
        }
        return false;
    }

    private Service getService(String serviceName) {
        Service service = services.stream().filter(s -> s.getName().equals(serviceName)).findFirst().orElse(null);
        return service;
    }

}
