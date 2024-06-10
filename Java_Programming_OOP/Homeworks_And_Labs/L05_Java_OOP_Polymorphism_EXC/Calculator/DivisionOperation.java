package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

import java.util.ArrayList;
import java.util.List;

public class DivisionOperation implements Operation {

    private List<Integer> operands;

    public DivisionOperation(){
        this.operands = new ArrayList<>();
    }

    @Override
    public void addOperand(int operand) {
        this.operands.add(operand);
    }

    @Override
    public boolean isCompleted() {
        return this.operands.size() == 2;
    }

    @Override
    public int getResult() {
        return this.operands.get(0) / this.operands.get(1);
    }

}
