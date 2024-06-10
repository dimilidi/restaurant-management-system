package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Word;

public class PasteTransform implements TextTransform {
    private final Clipboard clipboard;

    public PasteTransform(Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public void invokeOn(StringBuilder text, int startIndex, int endIndex) {
        String clipboard = this.clipboard.getCurrent();
        text.replace(startIndex, endIndex, clipboard);
    }
}
