package Homeworks_And_Labs.L04_Java_OOP_InterfacesAndAbstraction_LAB.CarShopExtended;

public interface Rentable extends Car{
    Integer getMinRentDay();
    Double getPricePerDay();
}