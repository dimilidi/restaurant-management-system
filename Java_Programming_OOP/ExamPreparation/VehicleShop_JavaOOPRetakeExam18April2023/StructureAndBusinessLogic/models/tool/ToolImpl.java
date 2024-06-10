package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.tool;

import static ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.common.ExceptionMessages.TOOL_POWER_LESS_THAN_ZERO;

public class ToolImpl implements Tool{
    private int power;

    public ToolImpl(int power) {
        setPower(power);
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void decreasesPower() {
        setPower(Math.max(0, power - 5));
    }

    @Override
    public boolean isUnfit() {
        return power == 0;
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(TOOL_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }
}
