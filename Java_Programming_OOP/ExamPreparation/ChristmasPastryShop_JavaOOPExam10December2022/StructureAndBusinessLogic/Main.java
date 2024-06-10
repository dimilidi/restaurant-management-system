package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic;

import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.core.ControllerImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.core.EngineImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.core.interfaces.Controller;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.interfaces.Booth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies.interfaces.Delicacy;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.io.ConsoleReader;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.io.ConsoleWriter;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.BoothRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.CocktailRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.DelicacyRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.BoothRepository;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.CocktailRepository;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.DelicacyRepository;

public class Main {
    public static void main(String[] args) {

        String a = " ";
        int a1 = a.length();
        DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl();
        CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl();
        BoothRepository<Booth> boothRepository = new BoothRepositoryImpl();

        Controller controller = new ControllerImpl(delicacyRepository, cocktailRepository, boothRepository);

        // TODO:OPTIONAL

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

    }
}
