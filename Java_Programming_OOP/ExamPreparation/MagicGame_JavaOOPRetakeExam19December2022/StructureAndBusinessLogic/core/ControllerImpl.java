package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.core;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians.BlackWidow;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians.Magician;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magicians.Wizard;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.BlackMagic;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.Magic;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.magics.RedMagic;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.region.Region;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.models.region.RegionImpl;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.repositories.interfaces.MagicRepositoryImpl;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.repositories.interfaces.MagicianRepositoryImpl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.ExceptionMessages.*;
import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.OutputMessages.SUCCESSFULLY_ADDED_MAGIC;
import static ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.StructureAndBusinessLogic.common.OutputMessages.SUCCESSFULLY_ADDED_MAGICIAN;

public class ControllerImpl implements Controller {
    private MagicRepositoryImpl magics;
    private MagicianRepositoryImpl magicians;
    private Region region;

    public ControllerImpl() {
        magics = new MagicRepositoryImpl();
        magicians = new MagicianRepositoryImpl();
        region = new RegionImpl();
    }

    @Override
    public String addMagic(String type, String name, int bulletsCount) {
        Magic magic;
        switch (type){
            case "RedMagic":
                magic = new RedMagic(name, bulletsCount);
                break;
            case "BlackMagic":
                magic = new BlackMagic(name, bulletsCount);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGIC_TYPE);
        }
        magics.addMagic(magic);
        return String.format(SUCCESSFULLY_ADDED_MAGIC, name);
    }

    @Override
    public String addMagician(String type, String username, int health, int protection, String magicName) {
        Magician magician;
        Magic magic = (Magic) magics.findByName(magicName);
        if (magic == null){
            throw new NullPointerException(MAGIC_CANNOT_BE_FOUND);
        }
        switch (type){
            case "Wizard":
                magician = new Wizard(username, health, protection, magic);
                break;
            case "BlackWidow":
                magician = new BlackWidow(username, health, protection, magic);
                break;
            default:
                throw new IllegalArgumentException(INVALID_MAGICIAN_TYPE);
        }
        magicians.addMagician(magician);
        return String.format(SUCCESSFULLY_ADDED_MAGICIAN, username);
    }


    @Override
    public String startGame() {
        return region.start(magicians.getData());
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        List<Magician> magicianList = magicians.getData().stream().sorted(Comparator.comparing(Magician::getHealth).thenComparing(Magician::getUsername)).collect(Collectors.toList());
        for (Magician magician : magicianList) {
            int health = magician.getHealth();
            if (magician.getHealth() < 0){
                health = 0;
            }
            int protection = magician.getProtection();
            if (magician.getProtection() < 0){
                protection = 0;
            }
            sb.append(String.format("%s: %s", magician.getClass().getSimpleName(), magician.getUsername())).append(System.lineSeparator())
                    .append(String.format("Health: %d", health)).append(System.lineSeparator())
                    .append(String.format("Protection: %d", protection)).append(System.lineSeparator())
                    .append(String.format("Magic: %s", magician.getMagic().getName())).append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
