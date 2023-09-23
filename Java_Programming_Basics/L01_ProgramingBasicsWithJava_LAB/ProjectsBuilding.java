package L01_ProgramingBasicsWithJava_LAB;

import java.util.Scanner;

public class ProjectsBuilding {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        int countProjects = Integer.parseInt(scanner.nextLine());
        int hours = countProjects * 3;
        System.out.printf("The architect %s will need %d hours to complete %d projects.", name, hours, countProjects);
    }
}
