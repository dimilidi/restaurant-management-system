package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

public class MemorySaveOperation implements Operation {
    private final Memory memory;
    private int lastSaved;
    private boolean completed;

    public MemorySaveOperation(Memory memory) {
        this.memory = memory;
    }

    @Override
    public void addOperand(int operand) {
        memory.save(operand);
        lastSaved = operand;
        this.completed = true;
    }

    @Override
    public int getResult() {
        return this.lastSaved;
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
}
