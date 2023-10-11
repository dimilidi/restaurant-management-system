package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class StudentAcademy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int countInput = Integer.parseInt(scanner.nextLine());
        Map<String, List> studentMap = new LinkedHashMap<>();

        for (int i = 0; i < countInput; i++) {
            String student = scanner.nextLine();
            double grade = Double.parseDouble(scanner.nextLine());

            if (!studentMap.containsKey(student)) {
                List<Double> gradesList = new ArrayList<>();
                studentMap.put(student, gradesList);
            }
            studentMap.get(student).add(grade);
        }

        for (Map.Entry<String, List> entry : studentMap.entrySet()) {
            String student = entry.getKey();
            List gradesList = entry.getValue();
            double sum = 0.0;

            for (int i = 0; i < gradesList.size(); i++) {
                double grade = (double) entry.getValue().get(i);
                sum += grade;
            }

            double avrgGrade = sum / gradesList.size();
            if (avrgGrade >= 4.50) {
                System.out.printf("%s -> %.2f%n", student, avrgGrade);
            }
        }
    }
}
