package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Word;

public class CutTransform implements TextTransform{

    private final Clipboard clipboard;

    public CutTransform(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String cutResult = text.substring(startIndex, endIndex);
        this.clipboard.setCurrent(cutResult);
        text.replace(startIndex, endIndex, "");
    }
}
