package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.Vehicles;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final String CAR_NAME = "Car";
    public static final String TRUCK_NAME = "Truck";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DecimalFormat decimalFormat = new DecimalFormat("###.##");

        String[] carInfoParams = scanner.nextLine().split("\\s+");
        String[] truckInfoParams = scanner.nextLine().split("\\s+");

        Map<String, Vehicle> vehicleByName = new LinkedHashMap<>();
        vehicleByName.put(CAR_NAME, new Car(Double.parseDouble(carInfoParams[1]), Double.parseDouble((carInfoParams[2]))));
        vehicleByName.put( TRUCK_NAME, new Truck(Double.parseDouble(truckInfoParams[1]), Double.parseDouble((truckInfoParams[2]))));

        int numCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numCommands; i++) {
            String[] commandParts = scanner.nextLine().split("\\s+");

            String operation = commandParts[0];
            String vehicleName = commandParts[1];
            Vehicle vehicle = vehicleByName.get(vehicleName);

            switch (operation) {
                case "Drive":
                    Double travelledKm = vehicle.drive(Double.parseDouble(commandParts[2]));
                    if(travelledKm != null) {
                        System.out.printf("%s travelled %s km%n", vehicleName, decimalFormat.format(travelledKm));
                    } else {
                        System.out.printf("%s needs refueling%n", vehicleName);
                    }
                    break;
                case "Refuel":
                    vehicle.refuel(Double.parseDouble(commandParts[2]));

                    break;
                default:
                    throw new IllegalStateException("Unknown command " + commandParts[0]);
            }
        }

        System.out.printf("Car: %.2f%n", vehicleByName.get(CAR_NAME).getFuelQuantity());
        System.out.printf("Truck: %.2f%n", vehicleByName.get(TRUCK_NAME).getFuelQuantity());
    }
}
