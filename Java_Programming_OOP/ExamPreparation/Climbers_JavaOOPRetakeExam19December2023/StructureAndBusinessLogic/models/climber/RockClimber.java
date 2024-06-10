package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber;

public class RockClimber extends BaseClimber{
    public RockClimber(String name) {
        super(name, 120);
    }

    @Override
    public void climb() {
        decreaseStrength(60);
    }
}
