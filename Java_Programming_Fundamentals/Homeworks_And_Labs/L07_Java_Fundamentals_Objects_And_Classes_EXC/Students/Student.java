package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.Students;

public class Student {
    String firstName;
    String lastName;
    double grade;

    public  Student (String firstName, String lastName, double grade){
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        String formatedString = String.format("%s %s: %.2f", firstName, lastName, grade);
        return formatedString;
    }


}
