package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations;

import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Implementations.SoldierImpl;
import Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_EXC.MilitaryElite.Interfaces.Private;

public class PrivateImpl extends SoldierImpl implements Private {
    private double salary;

    public PrivateImpl(int id, String firstName, String lastName, Double salary) {
        super(id, firstName, lastName);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("Name: %s %s Id: %d Salary: %.2f", getFirstName(), getLastName(), getId(), getSalary());
    }
}