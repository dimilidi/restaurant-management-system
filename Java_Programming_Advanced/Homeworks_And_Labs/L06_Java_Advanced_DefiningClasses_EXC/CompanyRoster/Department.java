package Homeworks_And_Labs.L06_Java_Advanced_DefiningClasses_EXC.CompanyRoster;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private List<Employee> employeeList;

    public Department(String name) {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }

    public double getAverageSalary() {
        /*double sumSalary = 0;
        for (Employee employee : employees) {
            sumSalary += employee.getSalary();
        }
        return sumSalary / employees.size();*/
        return this.employeeList
                .stream()
                .mapToDouble(e -> e.getSalary())
                .average()
                .orElse(0.0);
    }

    public List<Employee> getEmployees() {
        return this.employeeList;
    }

    public String getName() {
        return this.name;
    }
}
