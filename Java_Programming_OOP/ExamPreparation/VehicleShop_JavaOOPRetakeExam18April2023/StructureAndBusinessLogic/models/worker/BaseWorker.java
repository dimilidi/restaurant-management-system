package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker;

import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool.Tool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.WORKER_NAME_NULL_OR_EMPTY;
import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.WORKER_STRENGTH_LESS_THAN_ZERO;


public abstract class BaseWorker implements Worker{

    private String name;
    private int strength;
    private Collection<Tool> tools = new ArrayList<>();

    protected BaseWorker(String name, int strength) {
        setName(name);
        setStrength(strength);
    }

    @Override
    public void working() {
        setStrength(Math.max(0, strength - 10));
    }

    @Override
    public void addTool(Tool tool) {
        tools.add(tool);
    }

    @Override
    public boolean canWork() {
        return strength > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }

    @Override
    public Collection<Tool> getTools() {
        return Collections.unmodifiableCollection(tools);
    }

    @Override
    public String toString() {
        List<Tool> fitTools = tools.stream().filter(t -> !t.isUnfit()).collect(Collectors.toList());

        return String.format("Name: %s, Strength: %d%nTools: %d fit left", name, strength, fitTools.size());
    }

    private void setName(String name) {
        if(name == null || name.equals("")) {
            throw new IllegalArgumentException(WORKER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setStrength(int strength) {
        if(strength < 0) {
            throw new IllegalArgumentException(WORKER_STRENGTH_LESS_THAN_ZERO);
        }
        this.strength = strength;
    }

    protected void decreaseStrength(int amount) {
        setStrength(Math.max(0, strength - amount));
    }
}
