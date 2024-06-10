package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.services;


import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.robot.Robot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.entities.supplements.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import static ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.common.ConstantMessages.NOT_ENOUGH_CAPACITY_FOR_ROBOT;
import static ExamPreparation.RobotService_JavaOOPRegularExam8April2023.StructureAndBusinessLogic.common.ExceptionMessages.SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY;


public abstract class BaseService implements Service{

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Robot> robots;

    protected BaseService(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.supplements = new ArrayList<>();
        this.robots = new ArrayList<>();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        if(name == null || name.trim().isEmpty()) {
            throw new NullPointerException(SERVICE_NAME_CANNOT_BE_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public Collection<Robot> getRobots() {
        return Collections.unmodifiableCollection(robots);
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return Collections.unmodifiableCollection(supplements);
    }

    @Override
    public void addRobot(Robot robot) {
        if  (capacity < robots.size()) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_ROBOT);
        }
            robots.add(robot);
    }

    @Override
    public void removeRobot(Robot robot) {
        robots.remove(robot);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void feeding() {
        for (Robot robot : robots) {
            robot.eating();
        }
    }

    @Override
    public int sumHardness() {
        return supplements.stream().mapToInt(Supplement::getHardness).sum();
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        String robotNames = this.robots.stream()
                .map(Robot::getName)
                .collect(Collectors.joining(" "));

        sb.append(String.format("%s %s:%n", name, this.getClass().getSimpleName()));
        sb.append(String.format("Robots: %s%n", robots.isEmpty() ? "none" : robotNames));
        sb.append(String.format("Supplements: %d Hardness: %d", supplements.size(), sumHardness() ));

        return sb.toString().trim();
    }
}
