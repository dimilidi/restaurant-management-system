package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team;

public class Bulgaria extends BaseTeam{
    public Bulgaria(String name, String country, int advantage) {
        super(name, country, advantage);
    }

    @Override
    public void play() {
        super.increaseAdvantage(115);
    }
}
