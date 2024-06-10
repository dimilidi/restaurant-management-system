package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

public class ExtendedInputInterpreter extends InputInterpreter{
    private Memory memory;
    public ExtendedInputInterpreter(CalculationEngine engine) {
        super(engine);
        memory = new Memory();
    }

    @Override
    public Operation getOperation(String operationName) {
        Operation operation = super.getOperation(operationName);

        if(operation != null) {
            return operation;
        }

        if(operationName.equals("/")) {
            return new DivisionOperation();
        }

        if(operationName.equals("ms")) {
            return new MemorySaveOperation(memory);
        }

        if(operationName.equals("mr")) {
            return new MemoryRecallOperation(memory);
        }

        return null;
    }
}
