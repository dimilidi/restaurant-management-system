package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Enums.Corps;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations.PrivateImpl;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.SpecialisedSoldier;

public class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {
    Corps corp;

    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary);
        setCorp(corp);
    }

    private void setCorp(String corp) {
        if (!corp.equals("Airforces") && !corp.equals("Marines")) {
            throw new IllegalArgumentException("Invalid corp!");
        }
        this.corp = Corps.valueOf(corp);
    }

    @Override
    public Corps getCorp() {
        return corp;
    }
}