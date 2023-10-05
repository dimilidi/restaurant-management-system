package Homeworks_And_Labs.L07_Java_Fundamentals_Objects_And_Classes_EXC.VehicleCatalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command = scanner.nextLine();
        List<Vehicle> vehicleList = new ArrayList<>();

        while (!command.equalsIgnoreCase("end")) {
            String[] commandParts = command.split(" ");
            String type = commandParts[0];
            String model = commandParts[1];
            String color = commandParts[2];
            int horsepower = Integer.parseInt(commandParts[3]);

            Vehicle vehicle = new Vehicle(type, model, color, horsepower);
            vehicleList.add(vehicle);

            command = scanner.nextLine();
        }

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("Close the Catalogue")) {
            for (int i = 0; i < vehicleList.size(); i++) {
                Vehicle vehicle = vehicleList.get(i);
                System.out.print(vehicle.printVehicle(input));
            }
            input = scanner.nextLine();
        }

        int countCars = 0;
        double averageHpCars = 0.0;
        int totalHorsepowerCars = 0;
        int countTrucks = 0;
        double averageHpTrucks = 0.0;
        int totalHorsepowerTrucks = 0;

        for (Vehicle vehicle : vehicleList) {
            int hp = vehicle.getHorsepower();

            if (vehicle.getType().contains("car")) {
                countCars++;
                totalHorsepowerCars += hp;
            } else if (vehicle.getType().contains("truck")) {
                countTrucks++;
                totalHorsepowerTrucks += hp;
            }
        }

        if (countCars > 0) {
             averageHpCars = totalHorsepowerCars / (countCars * 1.0);
        }
        if (countTrucks > 0) {
             averageHpTrucks = totalHorsepowerTrucks / (countTrucks * 1.0);
        }

        System.out.printf("Cars have average horsepower of: %.2f.%n", averageHpCars);
        System.out.printf("Trucks have average horsepower of: %.2f.%n", averageHpTrucks);
    }
}
