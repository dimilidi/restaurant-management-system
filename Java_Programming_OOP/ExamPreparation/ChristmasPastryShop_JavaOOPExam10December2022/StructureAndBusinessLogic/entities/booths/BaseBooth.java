package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths;


import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.booths.interfaces.Booth;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.cocktails.interfaces.Cocktail;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.StructureAndBusinessLogic.common.ExceptionMessages.INVALID_TABLE_CAPACITY;


public abstract class BaseBooth implements Booth {
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private  int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();
        this.boothNumber = boothNumber;

        if (capacity < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.capacity = capacity;

        this.pricePerPerson = pricePerPerson;
        isReserved = false;
    }


    @Override
    public int getBoothNumber() {
        return boothNumber;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean isReserved() {
        return isReserved;
    }

    @Override
    public double getPrice() {
        return price;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.price = numberOfPeople * this.pricePerPerson;
    }

    @Override
    public double getBill() {
        double bill = this.price;
        for (Delicacy delicacyOrder : this.delicacyOrders) {
            bill += delicacyOrder.getPrice();
        }
        for (Cocktail cocktailOrder : this.cocktailOrders) {
            bill += cocktailOrder.getPrice();
        }
        return bill;
    }

    @Override
    public void clear() {
        delicacyOrders.clear();
        cocktailOrders.clear();
        numberOfPeople = 0;
        isReserved = false;
        price = 0.0;
    }
}
