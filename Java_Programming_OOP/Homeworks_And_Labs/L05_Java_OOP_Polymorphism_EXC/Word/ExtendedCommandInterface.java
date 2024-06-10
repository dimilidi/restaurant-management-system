package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Word;

import java.util.List;

public class ExtendedCommandInterface extends CommandImpl{

    private final Clipboard clipboard;

    public ExtendedCommandInterface(StringBuilder text) {
        super(text);
        this.clipboard = new Clipboard();
    }

    @Override
    protected List<Command> initCommands() {
        List<Command> commands = super.initCommands();

        commands.add(new Command("cut", new CutTransform(clipboard)));
        commands.add(new Command("paste", new PasteTransform(clipboard)));

        return commands;
    }
}
