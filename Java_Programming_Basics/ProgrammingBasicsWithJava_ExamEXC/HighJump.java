package ProgrammingBasicsWithJava_ExamEXC;
import java.util.Scanner;

public class HighJump {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int desiredHeight = Integer.parseInt(scanner.nextLine());
        int currentHeight = desiredHeight - 30;
        boolean isSuccess = false;
        int consecutiveFails = 0;
        int totalJumps = 0;

        while (!isSuccess) {
            totalJumps++;
            int jumpHeight = Integer.parseInt(scanner.nextLine());

            if (jumpHeight > currentHeight) {
                currentHeight += 5;
                consecutiveFails = 0;
            } else {
                consecutiveFails++;
            }

            if (currentHeight > desiredHeight) {
                isSuccess = true;
                break;
            }

            if (consecutiveFails == 3) {
                break;
            }
        }

        if (isSuccess) {
            System.out.printf("Tihomir succeeded, he jumped over %dcm after %d jumps.", desiredHeight, totalJumps);
        } else {
            System.out.printf("Tihomir failed at %dcm after %d jumps.", currentHeight, totalJumps);
        }
    }
}
