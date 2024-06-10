package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Word;

public class Initialization {
    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface commandInterface =  new ExtendedCommandInterface(text);
        commandInterface.init();
        return commandInterface;
    }
}
