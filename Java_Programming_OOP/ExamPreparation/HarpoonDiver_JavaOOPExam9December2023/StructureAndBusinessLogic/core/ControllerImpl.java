package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.core;


import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.DeepWaterDiver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.Diver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.OpenWaterDiver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diver.WreckDiver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diving.Diving;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.diving.DivingImpl;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.divingSite.DivingSite;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.models.divingSite.DivingSiteImpl;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.repositories.DiverRepository;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.repositories.DivingSiteRepository;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.StructureAndBusinessLogic.common.ExceptionMessages.*;


public class ControllerImpl implements Controller{
    private DiverRepository diverRepository= new DiverRepository();
    private DivingSiteRepository divingSiteRepository = new DivingSiteRepository();
    private Diving diving = new DivingImpl();
    private int divingCount;

    @Override
    public String addDiver(String kind, String diverName) {
        Diver diver;
        switch(kind) {
            case "OpenWaterDiver":
                diver = new OpenWaterDiver(diverName);
                break;
            case "WreckDiver":
                diver = new WreckDiver(diverName);
                break;
            case "DeepWaterDiver":
                diver = new DeepWaterDiver(diverName);
                break;
            default:
                throw new IllegalArgumentException(DIVER_INVALID_KIND);
        }

        diverRepository.add(diver);
        return String.format(DIVER_ADDED, kind, diverName);
    }

    @Override
    public String addDivingSite(String siteName, String... seaCreatures) {
        DivingSite divingSite = new DivingSiteImpl(siteName);
        for (String seaCreature : seaCreatures) {
            divingSite.getSeaCreatures().add(seaCreature);
        }

        divingSiteRepository.add(divingSite);

        return String.format(DIVING_SITE_ADDED, siteName);
    }

    @Override
    public String removeDiver(String diverName) {
        Diver diverToRemove = diverRepository.byName(diverName);

        if (diverToRemove == null) {
            throw new IllegalArgumentException(String.format(DIVER_DOES_NOT_EXIST, diverName));
        }

        diverRepository.remove(diverToRemove);
        return String.format(DIVER_REMOVE, diverName);
    }

    @Override
    public String startDiving(String siteName) {
        List<Diver> divers = diverRepository
                .getCollection()
                .stream()
                .filter(d -> d.getOxygen() > 30)
                .collect(Collectors.toList());

        if (divers.isEmpty()) {
            throw new IllegalArgumentException(SITE_DIVERS_DOES_NOT_EXISTS);
        }

        DivingSite divingSite = divingSiteRepository.byName(siteName);
        diving.searching(divingSite, divers);

        divingCount++;

        Iterator<Diver> iterator = divers.iterator();
        int removedDiversCount = 0;
        while (iterator.hasNext()) {
            Diver diver = iterator.next();
            if (diver.getOxygen() == 0) {
                iterator.remove();
                removedDiversCount++;
            }
        }

        return String.format(SITE_DIVING, siteName, removedDiversCount);
    }


    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_DIVING_SITES, divingCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_DIVERS_STATISTICS);

        for (Diver diver : diverRepository.getCollection()) {
            sb.append(System.lineSeparator());
            sb.append(diver.toString());
        }
        return sb.toString().trim();
    }
}
