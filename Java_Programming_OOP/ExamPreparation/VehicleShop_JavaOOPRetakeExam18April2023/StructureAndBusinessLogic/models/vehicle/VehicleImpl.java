package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle;

import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.VEHICLE_NAME_NULL_OR_EMPTY;
import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.VEHICLE_STRENGTH_LESS_THAN_ZERO;

public class VehicleImpl implements Vehicle{

    private String name;
    private int strengthRequired;

    public VehicleImpl(String name, int strengthRequired) {
        setName(name);
        setStrengthRequired(strengthRequired);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrengthRequired() {
        return strengthRequired;
    }

    @Override
    public boolean reached() {
        return strengthRequired == 0;
    }

    @Override
    public void making() {
        setStrengthRequired(Math.max(0, strengthRequired - 5));
    }

    private void setName(String name) {
        if(name == null || name.equals("")) {
            throw new IllegalArgumentException(VEHICLE_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    private void setStrengthRequired(int strengthRequired) {
        if(strengthRequired < 0) {
            throw new IllegalArgumentException(VEHICLE_STRENGTH_LESS_THAN_ZERO);
        }
        this.strengthRequired = strengthRequired;
    }
}
