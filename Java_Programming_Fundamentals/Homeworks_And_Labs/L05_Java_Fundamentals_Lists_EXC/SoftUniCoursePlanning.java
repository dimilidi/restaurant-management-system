package Homeworks_And_Labs.L05_Java_Fundamentals_Lists_EXC;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SoftUniCoursePlanning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<String> courseSchedule = Arrays
                .stream(scanner.nextLine().split(", "))
                .collect(Collectors.toList());

        String command = scanner.nextLine();

        while (!command.equalsIgnoreCase("course start")) {
            String[] commandParts = command.split(":");
            String action = commandParts[0];
            String title = commandParts[1];

            if (action.contains("Add") && !courseSchedule.contains(title)) {
                courseSchedule.add(title);
            } else if (action.contains("Insert") && !courseSchedule.contains(title)) {
                int index = Integer.parseInt(commandParts[2]);
                courseSchedule.add(index, title);
            } else if (action.contains("Remove") && courseSchedule.contains(title)) {
                courseSchedule.remove(title);

                int indexLesson = courseSchedule.indexOf(title);
                if (courseSchedule.get(indexLesson + 1).equals(title + "-Exercise")) {
                    courseSchedule.remove(indexLesson + 1);
                }
            } else if (action.contains("Swap")) {
                String secondTitle = commandParts[2];
                if (courseSchedule.contains(secondTitle) && courseSchedule.contains(title)) {
                    int index1 = courseSchedule.indexOf(title);
                    int index2 = courseSchedule.indexOf(secondTitle);
                    courseSchedule.set(index1, secondTitle);
                    courseSchedule.set(index2, title);

                    String Exc1 = title + "-Exercise";
                    String Exc2 = secondTitle + "-Exercise";
                    if (courseSchedule.contains(Exc1)) {
                        int indexExc1 = courseSchedule.indexOf(title) + 1;
                        courseSchedule.remove(courseSchedule.indexOf(Exc1));
                        courseSchedule.add(indexExc1, Exc1);
                    }

                    if (courseSchedule.contains(Exc2)) {
                        int indexExc2 = courseSchedule.indexOf(secondTitle) + 1;
                        courseSchedule.remove(courseSchedule.indexOf(Exc2));
                        courseSchedule.add(indexExc2, Exc2);
                    }
                }
            } else if (action.contains("Exercise")) {
                String exc = title + "-Exercise";
                int indexTitle = courseSchedule.indexOf(title);
                if (courseSchedule.contains(title)) {
                    if (indexTitle == courseSchedule.size() - 1) {
                        courseSchedule.add(indexTitle + 1, exc);
                    } else if (!courseSchedule.get(indexTitle + 1).equals(exc)) {
                        courseSchedule.add(indexTitle + 1, exc);
                    }
                } else {
                    courseSchedule.add(title);
                    courseSchedule.add(exc);
                }
            }
            command = scanner.nextLine();
        }

        for (String course : courseSchedule) {
            int index = courseSchedule.indexOf(course) + 1;
            System.out.printf("%d.%s%n", index, course);
        }

    }
}
