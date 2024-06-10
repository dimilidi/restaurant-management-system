package Homeworks_And_Labs.L01_Java_OOP_Working_With_Abstractions_EXC.TrafficLights;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] signals = scanner.nextLine().split("\\s+");

        TrafficLight[] trafficLights = new TrafficLight[signals.length];

        for (int i = 0; i < signals.length; i++) {
            String s = signals[i];
            TrafficLight.Signal signal = TrafficLight.Signal.valueOf(s);
            trafficLights[i] = new TrafficLight(signal);
        }

        int timesToChangeSignal = scanner.nextInt();
        for (int i = 0; i < timesToChangeSignal; i++) {
            updateTrafficLights(trafficLights);
            printTrafficLights(trafficLights);
        }


    }

    private static void printTrafficLights(TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            System.out.print(trafficLight.getSignal() + " ");
        }
        System.out.println();
    }

    private static void updateTrafficLights(TrafficLight[] trafficLights) {
        for (TrafficLight trafficLight : trafficLights) {
            trafficLight.update();
        }

    }
}
