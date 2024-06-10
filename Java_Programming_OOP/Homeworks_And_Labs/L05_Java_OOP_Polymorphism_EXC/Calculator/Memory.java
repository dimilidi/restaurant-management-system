package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class Memory {
    Deque<Integer> stack;

    public Memory() {
        this.stack = new ArrayDeque<>();
    }

    public void save(int operand) {
        this.stack.push(operand);
    }

    public int recall() {
        return this.stack.pop();
    }

}
