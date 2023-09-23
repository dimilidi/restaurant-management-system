package L04_ProgrammingBasicsWithJava_LAB;

import java.util.Scanner;

public class EvenPowersOf2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        int base = 2;

        for (int i = 0; i <= n; i ++) {
          double result =  Math.pow(base, i);
          if ((i % 2) == 0) {
              System.out.printf("%.0f%n",result);
          }
        }
    }
}
