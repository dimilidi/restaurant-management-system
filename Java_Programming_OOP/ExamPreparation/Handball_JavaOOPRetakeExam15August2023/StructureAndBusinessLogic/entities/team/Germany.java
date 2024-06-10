package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team;

public class Germany extends BaseTeam{
    public Germany(String name, String country, int advantage) {
        super(name, country, advantage);
    }


    @Override
    public void play() {
        super.increaseAdvantage(145);
    }
}
