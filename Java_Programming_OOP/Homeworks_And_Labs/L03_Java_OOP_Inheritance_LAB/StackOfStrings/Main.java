package Homeworks_And_Labs.L03_Java_OOP_Inheritance_LAB.StackOfStrings;

public class Main {
    public static void main(String[] args) {
        StackOfStrings StackOfStr = new StackOfStrings();
        StackOfStr.push("one");
        StackOfStr.push("two");
        StackOfStr.push("three");

        System.out.println(StackOfStr.isEmpty());
        System.out.println(StackOfStr.peek());
        System.out.println(StackOfStr.pop());
        System.out.println(StackOfStr.pop());
        System.out.println(StackOfStr.pop());
    }
}
