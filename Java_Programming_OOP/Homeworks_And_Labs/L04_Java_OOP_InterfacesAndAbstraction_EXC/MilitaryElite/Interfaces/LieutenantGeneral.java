package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces;

import java.util.Collection;

public interface LieutenantGeneral extends Soldier {
    void addPrivate(Soldier soldier);

    Collection<Soldier> getPrivates();
}