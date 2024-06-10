package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

public class MemoryRecallOperation implements Operation {
    private final Memory memory;

    public MemoryRecallOperation(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {

    }

    @Override
    public int getResult() {
        return memory.recall();
    }

    @Override
    public boolean isCompleted() {
        return true;
    }
}
