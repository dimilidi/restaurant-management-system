package Homeworks_And_Labs.L05_Java_OOP_Polymorphism_EXC.VehiclesExtension;

import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final String CAR_NAME = "Car";
    public static final String TRUCK_NAME = "Truck";
    private static final String BUS_NAME = "Bus";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        DecimalFormat decimalFormat = new DecimalFormat("###.##");

        String[] carInfoParams = scanner.nextLine().split("\\s+");
        String[] truckInfoParams = scanner.nextLine().split("\\s+");
        String[] busInfoParams = scanner.nextLine().split("\\s+");

        Map<String, Vehicle> vehicleByName = new LinkedHashMap<>();
        vehicleByName.put(CAR_NAME, new Car(Double.parseDouble(carInfoParams[1]), Double.parseDouble((carInfoParams[2])), Double.parseDouble((carInfoParams[3]))));
        vehicleByName.put( TRUCK_NAME, new Truck(Double.parseDouble(truckInfoParams[1]), Double.parseDouble((truckInfoParams[2])), Double.parseDouble((truckInfoParams[3]))));
        vehicleByName.put( BUS_NAME, new Bus(Double.parseDouble(busInfoParams[1]), Double.parseDouble((busInfoParams[2])), Double.parseDouble((busInfoParams[3]))));

        int numCommands = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numCommands; i++) {
            String[] commandParts = scanner.nextLine().split("\\s+");

            String operation = commandParts[0];
            String vehicleName = commandParts[1];
            Vehicle vehicle = vehicleByName.get(vehicleName);
            try {
                switch (operation) {
                    case "DriveEmpty":
                        Bus bus = (Bus) vehicleByName.get(BUS_NAME);
                        bus.setupEmptyDrive();
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
                        break;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }


        }

        System.out.printf("Car: %.2f%n", vehicleByName.get(CAR_NAME).getFuelQuantity());
        System.out.printf("Truck: %.2f%n", vehicleByName.get(TRUCK_NAME).getFuelQuantity());
        System.out.printf("Bus: %.2f%n", vehicleByName.get(BUS_NAME).getFuelQuantity());
    }


}
