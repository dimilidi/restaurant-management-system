package L06_ProgrammingBasicsWithJava_EXC;

import java.util.Scanner;

public class NumberPyramid {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int counter = 1;

        for (int i = 1; i <= n; i++) {
            System.out.printf("%n");
            for (int j = 1; j <= i ; j++) {
                if((counter <= n )) {
                    System.out.print(counter + " ");
                }
                counter++;
            }
        }
    }
}
