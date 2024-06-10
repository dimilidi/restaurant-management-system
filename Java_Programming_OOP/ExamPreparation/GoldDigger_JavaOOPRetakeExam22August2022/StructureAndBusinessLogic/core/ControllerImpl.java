package ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.core;


import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ConstantMessages;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.common.ExceptionMessages;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Anthropologist;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Archaeologist;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Discoverer;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.discoverer.Geologist;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.operation.Operation;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.spot.Spot;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.repositories.DiscovererRepository;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.repositories.SpotRepository;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.operation.OperationImpl;
import ExamPreparation.GoldDigger_JavaOOPRetakeExam22August2022.StructureAndBusinessLogic.models.spot.SpotImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ControllerImpl implements Controller{
    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private Operation operation;
    private  int inspectionCount;

    public ControllerImpl() {
        this.discovererRepository = new DiscovererRepository();
        this.spotRepository = new SpotRepository();
        //this.operation = new OperationImpl();
        this.inspectionCount = 0;
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer = null;

        if(kind.equals("Anthropologist")) {
            discoverer = new Anthropologist(discovererName);
        } else if (kind.equals("Archaeologist")) {
            discoverer = new Archaeologist(discovererName);
        } else if (kind.equals("Geologist")) {
            discoverer = new Geologist(discovererName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DISCOVERER_INVALID_KIND);
        }

        discovererRepository.add(discoverer);

        return String.format(ConstantMessages.DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);
        List<String> items = Arrays.asList(exhibits);
        spot.getExhibits().addAll(items);

        this.spotRepository.add(spot);

        return String.format(ConstantMessages.SPOT_ADDED, spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = this.discovererRepository.byName(discovererName);

        if(discoverer == null) {
            String msg = String.format(ExceptionMessages.DISCOVERER_DOES_NOT_EXIST, discovererName);
            throw new IllegalArgumentException(msg);
        }

        this.discovererRepository.remove(discoverer);

        return String.format(ConstantMessages.DISCOVERER_EXCLUDE, discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        List<Discoverer> discoverersWithEnergyGreaterThan45 = new ArrayList<>();
        for (Discoverer discoverer : this.discovererRepository.getCollection()) {
            if (discoverer.getEnergy() > 45) {
                discoverersWithEnergyGreaterThan45.add(discoverer);
            }
        }

        if (discoverersWithEnergyGreaterThan45.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }

        Spot spot = this.spotRepository.byName(spotName);
        Operation operation = new OperationImpl();
        operation.startOperation(spot, discoverersWithEnergyGreaterThan45);

        long excluded = 0;
        for (Discoverer discoverer : discoverersWithEnergyGreaterThan45) {
            if (discoverer.getEnergy() == 0) {
                excluded++;
            }
        }

        this.inspectionCount++;

        return String.format(ConstantMessages.INSPECT_SPOT, spotName, excluded);
    }

    @Override
    public String getStatistics() {
        String discoverers = this.discovererRepository
                .getCollection()
                .stream()
                .map(Discoverer::toString)
                .collect(Collectors.joining(System.lineSeparator()));


        StringBuilder out = new StringBuilder();
        out.append(String.format(ConstantMessages.FINAL_SPOT_INSPECT, this.inspectionCount));
        out.append(System.lineSeparator());
        out.append(ConstantMessages.FINAL_DISCOVERER_INFO);
        out.append(System.lineSeparator());
        out.append(discoverers);

        return out.toString();
    }
}
