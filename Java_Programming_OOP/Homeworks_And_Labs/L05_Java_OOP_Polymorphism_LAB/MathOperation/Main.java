package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.MathOperation;
public class Main {
    public static void main(String[] args)  {
        MathOperation mathOperation = new MathOperation();
        System.out.println(mathOperation.add(2, 2));
        System.out.println(mathOperation.add(3, 3, 3));
        System.out.println(mathOperation.add(4, 4, 4, 4));
    }
}
