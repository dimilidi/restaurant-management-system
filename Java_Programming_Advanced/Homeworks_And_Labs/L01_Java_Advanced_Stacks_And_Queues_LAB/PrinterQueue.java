package Homeworks_And_Labs.L01_Java_Advanced_Stacks_And_Queues_LAB;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class PrinterQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        Deque<String> printerQueue = new ArrayDeque<>();

        while(!"print".equals(line)) {
            if(line.equals("cancel")) {
                if(printerQueue.isEmpty()){
                    System.out.println("Printer is on standby");
                } else {
                    String canceledDocument = printerQueue.poll();
                    System.out.println("Canceled" + " " +  canceledDocument);
                }

            } else {
                printerQueue.offer(line);
            }
            line = scanner.nextLine();
        }

        while(!printerQueue.isEmpty()){
            System.out.println(printerQueue.poll());
        }
    }
}







