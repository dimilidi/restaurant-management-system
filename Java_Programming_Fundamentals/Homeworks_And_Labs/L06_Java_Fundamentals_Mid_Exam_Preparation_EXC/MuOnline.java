package Homeworks_And_Labs.L06_Java_Fundamentals_Mid_Exam_Preparation_EXC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MuOnline {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] roomsArr = scanner.nextLine().split("\\|");

        int health = 100;
        int bitcoins = 0;
        int visitedRooms = 0;

        while (health > 0 || visitedRooms < roomsArr.length) {
            if (visitedRooms == roomsArr.length) {
                System.out.printf("You've made it!%nBitcoins: %d%nHealth: %d%n", bitcoins, health);
                break;
            }

            if (health <= 0) {
                break;
            }

            for (int i = 0; i < roomsArr.length; i++) {
                String[] roomArr = roomsArr[i].split(" ");
                String room = roomArr[0];
                int number = Integer.parseInt((roomArr[1]));
                visitedRooms++;

                if (room.equalsIgnoreCase("potion")) {
                    int hp = number;
                    if ((health + hp) >= 100) {
                        hp = 100 - health;
                        health = 100;
                    } else {
                         health += number;
                    }
                    System.out.printf("You healed for %d hp.%n", hp);
                    System.out.printf("Current health: %d hp.%n", health);
                } else if (room.equalsIgnoreCase("chest")) {
                    System.out.printf("You found %d bitcoins.%n", number);
                    bitcoins += number;
                } else {
                    health -= number;
                    if (health > 0) {
                        System.out.printf("You slayed %s.%n", room);
                    } else {
                        System.out.printf("You died! Killed by %s.%n", room);
                        System.out.printf("Best room: %s%n", visitedRooms);
                        break;
                    }
                }
            }
        }
    }
}
