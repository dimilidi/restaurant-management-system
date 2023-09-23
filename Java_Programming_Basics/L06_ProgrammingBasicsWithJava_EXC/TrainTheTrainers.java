package L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class TrainTheTrainers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int jury = Integer.parseInt(scanner.nextLine());
        String presentation = scanner.nextLine();
        double finalAssessment = 0.0;
        int  presentationCount = 0;

        while (!presentation.equalsIgnoreCase("finish")){
            presentationCount++;

            double sumScore = 0.0;

            for (int i = 0; i < jury; i++) {
                double score = Double.parseDouble(scanner.nextLine());
                sumScore += score;
            }
            double avrgScore = sumScore / jury;
            System.out.printf("%s - %.2f.%n", presentation, avrgScore);

            presentation = scanner.nextLine();
            finalAssessment += avrgScore;
        }
        System.out.printf("Student's final assessment is %.2f.", finalAssessment / presentationCount);


    }
}
