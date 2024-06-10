package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_LAB.Shapes;

public abstract class Shape {
    private Double perimeter;
    private Double area;

    public Double getPerimeter() {
        return perimeter;
    }

    protected void setPerimeter(Double perimeter) {
        this.perimeter = perimeter;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public abstract double calculatePerimeter();
    public abstract double calculateArea();

}
