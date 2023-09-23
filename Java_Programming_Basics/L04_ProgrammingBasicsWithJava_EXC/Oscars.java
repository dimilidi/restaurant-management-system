package L04_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class Oscars {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String actor = scanner.nextLine();
        double scores = Double.parseDouble(scanner.nextLine());
        int juryCount = Integer.parseInt(scanner.nextLine());
        double scoresTotal = 0.0;
        double scoresJury = 0.0;

        for(int i = 0; i < juryCount; i++) {
            String juryPerson = scanner.nextLine();
            double scoresInput = Double.parseDouble(scanner.nextLine());
            scores += (juryPerson.length() * scoresInput) / 2;
            if(scores > 1250.5) break;
        }

        if(scores > 1250.5) {
            System.out.printf("Congratulations, %s got a nominee for leading role with %.1f!", actor, scores);
        } else {
            System.out.printf("Sorry, %s you need %.1f more!", actor, 1250.5 - scores);
        }
    }
}
