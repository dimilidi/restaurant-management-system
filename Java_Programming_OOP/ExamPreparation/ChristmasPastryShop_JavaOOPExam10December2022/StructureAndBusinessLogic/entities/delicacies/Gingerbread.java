package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies;

public class Gingerbread extends BaseDelicacy {
    private static final double INITIAL_GINGERBREAD_PORTION = 200;
    public Gingerbread(String name, double portion, double price) {
        super(name, INITIAL_GINGERBREAD_PORTION, price);
    }


}
