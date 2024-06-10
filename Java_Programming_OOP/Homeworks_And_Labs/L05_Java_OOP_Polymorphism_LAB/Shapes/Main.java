package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.Shapes;

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(4.0, 6.0);
        System.out.println(rectangle.calculateArea());
        System.out.println(rectangle.calculatePerimeter());

        Shape circle = new Circle(4.0);
        System.out.println(circle.calculateArea());
        System.out.println(circle.calculatePerimeter());
    }
}
