package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {
    private String codeNumber;

    public SpyImpl(int id, String firstName, String lastName, String codeNumber) {
        super(id, firstName, lastName);
        this.codeNumber = codeNumber;
    }

    @Override
    public String getCodeNumber() {
        return codeNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Name: %s %s Id: %d", getFirstName(), getLastName(), getId())).append(System.lineSeparator());
        stringBuilder.append(String.format("Code Number: %s", codeNumber));
        return stringBuilder.toString().trim();
    }
}