package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces;

import java.util.Collection;

public interface Commando {
    void addMission(Mission mission);

    Collection<Mission> getMissions();

}