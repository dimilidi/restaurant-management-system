package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.core;

import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.enums.BoothType;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.enums.CocktailType;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.enums.DelicacyType;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.core.interfaces.Controller;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.OpenBooth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.PrivateBooth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.interfaces.Booth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.cocktails.Hibernation;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.cocktails.MulledWine;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies.Gingerbread;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies.Stolen;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies.interfaces.Delicacy;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.BoothRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.CocktailRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.DelicacyRepositoryImpl;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.BoothRepository;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.CocktailRepository;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.repositories.interfaces.DelicacyRepository;

import static ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.ExceptionMessages.BOOTH_EXIST;
import static ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private double income;
    private DelicacyRepository<Delicacy> delicacyRepository = new DelicacyRepositoryImpl();
    private CocktailRepository<Cocktail> cocktailRepository = new CocktailRepositoryImpl();
    private BoothRepository<Booth> boothRepository = new BoothRepositoryImpl();

    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository, CocktailRepository<Cocktail> cocktailRepository, BoothRepository<Booth> boothRepository) {
    }


    @Override
    public String addDelicacy(String type, String name, double price) {
        Delicacy delicacy = delicacyRepository.getByName(name);
        if (delicacy != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        DelicacyType cdelicacyType = DelicacyType.valueOf(type);
        switch (cdelicacyType) {
            case Stolen:
                delicacy = new Stolen(name, 250, price);
                break;
            case Gingerbread:
                delicacy = new Gingerbread(name, 200, price);
                break;
        }

        delicacyRepository.add(delicacy);
        return String.format(DELICACY_ADDED, name, type);
    }


    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        Cocktail cocktail = cocktailRepository.getByName(name);
        if (cocktail != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        CocktailType cocktailType = CocktailType.valueOf(type);
        switch (cocktailType) {
            case Hibernation:
                cocktail = new Hibernation(name, size, 4.50, brand);
                break;
            case MulledWine:
                cocktail = new MulledWine(name, size, 3.50, brand);
                break;
        }

        cocktailRepository.add(cocktail);
        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        if (booth != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }

        BoothType tableType = BoothType.valueOf(type);
        switch (tableType) {
            case OpenBooth:
                booth = new OpenBooth(boothNumber, capacity, 2.50);
                break;
            case PrivateBooth:
                booth = new PrivateBooth(boothNumber, capacity, 3.50);
                break;
        }

        boothRepository.add(booth);
        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth booth = boothRepository.getAll()
                .stream()
                .filter(b -> !b.isReserved())
                .filter(b -> b.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (booth != null) {
            booth.reserve(numberOfPeople);
            return String.format(BOOTH_RESERVED, booth.getBoothNumber(), numberOfPeople);
        }
        return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);

    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);
        double bill = booth.getBill();
        this.income += bill;
        booth.clear();

        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, this.income);
    }
}
