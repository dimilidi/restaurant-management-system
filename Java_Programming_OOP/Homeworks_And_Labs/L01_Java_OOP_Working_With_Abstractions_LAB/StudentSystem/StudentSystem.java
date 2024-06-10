package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_LAB.StudentSystem;

import java.util.HashMap;
import java.util.Map;

public class StudentSystem {
    private static final double EXCELLENT_GRADE_LOWER_BOUND = 5.00;
    private static final double AVG_GRADE_LOWER_BOUND = 3.50;
    private Map<String, Student> students;

    public StudentSystem() {
        this.students = new HashMap<>();
    }

    public Map<String, Student> getStudents() {
        return this.students;
    }

    public void executeCommand(String[] commandParts) {
        String commandName = commandParts[0];
        if (commandName.equals("Create")) {
            createStudent(commandParts);
        } else if (commandName.equals("Show")) {
            showStudent(commandParts);
        }
    }

    private void showStudent(String[] commandParts) {
        String name = commandParts[1];
        if (students.containsKey(name)) {
            Student student = students.get(name);
            StringBuilder output = new StringBuilder();
            output.append(String.format("%s",student.toString()));
            if (student.getGrade() >= EXCELLENT_GRADE_LOWER_BOUND) {
                output.append(String.format(" Excellent student."));
            } else if (student.getGrade() < EXCELLENT_GRADE_LOWER_BOUND && student.getGrade() >= AVG_GRADE_LOWER_BOUND) {
                output.append(String.format(" Average student."));
            } else {
                output.append(String.format(" Very nice person."));
            }
            System.out.printf("%s%n", output);
        }
    }

    private void createStudent(String[] commandParts) {
        String name = commandParts[1];
        int age = Integer.parseInt(commandParts[2]);
        double grade = Double.parseDouble(commandParts[3]);
        Student student = new Student(name, age, grade);
        students.putIfAbsent(name, student);
    }
}
