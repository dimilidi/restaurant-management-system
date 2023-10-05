package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.Students;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countStudents = Integer.parseInt(scanner.nextLine());
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < countStudents; i++) {
            String[] info = scanner.nextLine().split(" ");
            String firstName = info[0];
            String lastName = info[1];
            double grade = Double.parseDouble(info[2]);

            Student student = new Student(firstName, lastName, grade);
            students.add(student);
        }


        students.sort(Comparator.comparing(Student::getGrade).reversed());

        for (Student student : students) {
            System.out.println(student);
        }

    }
}
