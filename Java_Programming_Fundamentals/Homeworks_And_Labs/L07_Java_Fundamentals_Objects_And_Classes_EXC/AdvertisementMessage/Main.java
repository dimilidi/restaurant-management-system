package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.AdvertisementMessage;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numberMessages = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberMessages; i++) {
            Message message = new Message();
            System.out.println(message.generateMessage());
        }
    }
}
