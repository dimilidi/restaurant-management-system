package ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.entities.team;

import static ExamPreparation.Handball_JavaOOPRetakeExam15August2023.StructureAndBusinessLogic.common.ExceptionMessages.*;

public abstract class BaseTeam implements Team{
    private String name;
    private String country;
    private int advantage;

    public BaseTeam(String name, String country, int advantage) {
        setName(name);
        setCountry(country);
        setAdvantage(advantage);
    }


    @Override
    public void setName(String name) {
        if(name == null ||name.trim().isEmpty()) {
            throw new NullPointerException(TEAM_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAdvantage() {
        return advantage;
    }

    protected void increaseAdvantage(int value) {
        setAdvantage(advantage + value);
    }

    private void setCountry(String country) {
        if(country == null ||country.trim().isEmpty()) {
            throw new NullPointerException(TEAM_COUNTRY_NULL_OR_EMPTY);
        }
        this.country = country;
    }

    private void setAdvantage(int advantage) {
        if (advantage <= 0) {
            throw new IllegalArgumentException(TEAM_ADVANTAGE_BELOW_OR_EQUAL_ZERO);
        }
        this.advantage = advantage;
    }
}
