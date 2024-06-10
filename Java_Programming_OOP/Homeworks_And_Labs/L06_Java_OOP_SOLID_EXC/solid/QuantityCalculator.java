package Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid;

import Homeworks_And_Labs.L06_Java_OOP_SOLID_EXC.solid.products.HasWeight;

public class QuantityCalculator<T extends HasWeight> extends Calculator<T> {


    @Override
    protected double extractValue(T t) {
        return t.getWeightKg();
    }
}
