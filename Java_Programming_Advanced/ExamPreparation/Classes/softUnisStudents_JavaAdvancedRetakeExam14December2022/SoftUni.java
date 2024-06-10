package ExamPreparation.Classes.softUnisStudents_JavaAdvancedRetakeExam14December2022;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SoftUni {
    private int capacity;
    private List<Student> data;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public List<Student> getData() {
        return data;
    }

    public int getCount() {
       return data.size();
    }

    public String insert(Student student) {
        String result = "";
        Student foundStudent = getStudent(student.getFirstName(), student.getLastName());
        if(capacity > data.size()) {
            if(foundStudent != null) {
                result = "Student is already in the hall.";
            } else {
                data.add(student);
                result = String.format("Added student %s %s.", student.getFirstName(), student.getLastName());
            }
        } else {
            result = "The hall is full.";
        }

        return result;
    }

    public String remove(Student student) {
        String result = "";
        Student studentToRemove = getStudent(student.getFirstName(), student.getLastName());

        if(studentToRemove != null) {
            data.remove(student);
            result = String.format("Removed student %s %s.", student.getFirstName(), student.getLastName());
        } else {
            result = "Student not found.";
        }
        return result;
    }


    public Student getStudent(String firstName, String lastName) {
        return data.stream()
                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }

    public String getStatistics() {
        var sj = new StringJoiner("\n");
        sj.add("Hall size: " + getCount());
        data.stream().forEach(s -> sj.add(s.toString()));

        return sj.toString();
    }
}
