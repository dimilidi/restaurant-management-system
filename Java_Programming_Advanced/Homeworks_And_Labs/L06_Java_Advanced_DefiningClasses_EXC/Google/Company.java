package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.Google;

public class Company {
    private String companyName;
    private String department;
    private double salary;

    public Company(String companyName, String department, double salary) {
        this.companyName = companyName;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return companyName + " " + department + " " + String.format("%.2f", salary);
    }
}
