package Homeworks_And_Labs.L08_Java_Fundamentals_Associative_Arrays_EXC;

import java.util.*;

public class Courses {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();

        Map<String, List> courseMap = new LinkedHashMap<>();

        while (!command.equals("end")) {
            String[] course = command.split(" : ");
            String courseName = course[0];
            String student = course[1];

            if (!courseMap.containsKey(courseName)) {
                List<String> studentsList = new ArrayList<>();
                courseMap.put(courseName, studentsList);
            }
            courseMap.get(courseName).add(student);

            command = scanner.nextLine();
        }

        for (Map.Entry<String, List> entry : courseMap.entrySet()) {
            String course = entry.getKey();
            List<String> students = entry.getValue();
            int studentsCount = students.size();

            System.out.printf("%s: %d%n", course, studentsCount);
            for (String student : students){
                System.out.printf("-- %s%n", student);
            }
        }
    }
}
