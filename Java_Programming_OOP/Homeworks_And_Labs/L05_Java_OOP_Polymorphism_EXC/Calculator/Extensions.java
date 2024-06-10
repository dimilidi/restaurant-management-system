package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

public class Extensions {
    public static InputInterpreter buildInterpreter(CalculationEngine engine) {
        return new ExtendedInputInterpreter(engine);
    }
}
