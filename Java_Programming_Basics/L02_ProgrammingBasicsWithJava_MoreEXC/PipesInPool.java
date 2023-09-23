package L02_ProgrammingBasicsWithJava_MoreEXC;

import java.util.Scanner;

public class PipesInPool {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int poolVolumeLitres = Integer.parseInt(scanner.nextLine());
        int debitPipe1 = Integer.parseInt(scanner.nextLine());
        int debitPipe2 = Integer.parseInt(scanner.nextLine());
        double hoursWorkerAway = Double.parseDouble(scanner.nextLine());

        double totalDebitPipe1 = debitPipe1 * hoursWorkerAway;
        double totalDebitPipe2 = debitPipe2 * hoursWorkerAway;
        double totalDebitBothPipes = (debitPipe1 + debitPipe2) * hoursWorkerAway;
        double differencePoolVolumeDebitPipes = totalDebitBothPipes - poolVolumeLitres;
        double percentWaterInPool =  totalDebitBothPipes  / poolVolumeLitres * 100;
        double percentWaterPipe1 = totalDebitPipe1  / totalDebitBothPipes * 100 ;
        double percentWaterPipe2 = totalDebitPipe2 / totalDebitBothPipes * 100 ;

        if(percentWaterInPool <= 100) {
            System.out.printf("The pool is %.2f%% full. Pipe 1: %.2f%%. Pipe 2: %.2f%%.", percentWaterInPool, percentWaterPipe1, percentWaterPipe2);
        } else {
            System.out.printf("For %.2f hours the pool overflows with %.2f liters.",hoursWorkerAway,differencePoolVolumeDebitPipes);
        }
    }
}
