package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.core;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.Climber;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.RockClimber;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climber.WallClimber;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.climbing.ClimbingImpl;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain.Mountain;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.models.mountain.MountainImpl;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.repositories.ClimberRepository;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.repositories.MountainRepository;

import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ConstantMessages.*;
import static ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.StructureAndBusinessLogic.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ClimberRepository climberRepository = new ClimberRepository();
    private MountainRepository mountainRepository = new MountainRepository();
    private int climbingCount = 0;
    @Override
    public String addClimber(String type, String climberName) {
        Climber climber;

        switch (type) {
            case "RockClimber":
                climber = new RockClimber(climberName);
                break;
            case "WallClimber":
                climber = new WallClimber(climberName);
                break;
            default:
                throw new IllegalArgumentException(CLIMBER_INVALID_TYPE);
        }

        climberRepository.add(climber);
        return String.format(CLIMBER_ADDED, type, climberName);
    }

    @Override
    public String addMountain(String mountainName, String... peaks) {
        MountainImpl mountain = new MountainImpl(mountainName);
        for (String peak : peaks) {
            mountain.getPeaksList().add(peak);
        }
        mountainRepository.add(mountain);

        return String.format(MOUNTAIN_ADDED, mountainName);
    }

    @Override
    public String removeClimber(String climberName) {
        Climber climberToRemove = climberRepository.byName(climberName);
        if (climberToRemove == null) {
            throw new IllegalArgumentException(String.format(CLIMBER_DOES_NOT_EXIST, climberName));
        }

        climberRepository.remove(climberToRemove);
        return String.format(CLIMBER_REMOVE, climberName);

    }

    @Override
    public String startClimbing(String mountainName) {
        if (climberRepository.getCollection().isEmpty()) {
            throw new IllegalArgumentException(THERE_ARE_NO_CLIMBERS);
        }

        Mountain mountain = mountainRepository.byName(mountainName);

        ClimbingImpl climbing = new ClimbingImpl();
        climbing.conqueringPeaks(mountain, climberRepository.getCollection());

        climbingCount++;

        long removedClimberCount = climberRepository.getCollection().stream().filter(c -> !c.canClimb()).count();

        return String.format(PEAK_CLIMBING, mountainName, removedClimberCount);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_MOUNTAIN_COUNT, climbingCount));
        sb.append(System.lineSeparator());
        sb.append(FINAL_CLIMBERS_STATISTICS);

        for (Climber climber : climberRepository.getCollection()) {
            sb.append(System.lineSeparator());
            sb.append(climber.toString());
        }
        return sb.toString().trim();
    }
}
