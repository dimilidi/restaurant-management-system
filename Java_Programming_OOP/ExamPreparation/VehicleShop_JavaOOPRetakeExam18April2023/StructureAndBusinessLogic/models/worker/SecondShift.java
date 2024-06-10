package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.worker;

public class SecondShift extends BaseWorker{
    private static final int ADDITIONAL_STRENGTH_DECREASE = 15;
    public SecondShift(String name) {
        super(name, 70);
    }

    @Override
    public void working() {
        super.working();
        decreaseStrength(ADDITIONAL_STRENGTH_DECREASE);
    }
}
