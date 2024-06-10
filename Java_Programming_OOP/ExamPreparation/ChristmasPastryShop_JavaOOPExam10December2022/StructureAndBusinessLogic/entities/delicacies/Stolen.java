package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies;

public class Stolen extends BaseDelicacy {
    private static final double INITIAL_STOLEN_PORTION = 250;
    public Stolen(String name, double portion, double price) {
        super(name, INITIAL_STOLEN_PORTION, price);
    }
}
