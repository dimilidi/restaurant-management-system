package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.repositories;


import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.StructureAndBusinessLogic.models.vehicle.Vehicle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class VehicleRepository implements Repository<Vehicle> {

    Collection<Vehicle> vehicles = new ArrayList<>();
    @Override
    public Collection<Vehicle> getWorkers() {
        return Collections.unmodifiableCollection(vehicles);
    }

    @Override
    public void add(Vehicle model) {
        vehicles.add(model);
    }

    @Override
    public boolean remove(Vehicle model) {
        return vehicles.remove(model);
    }

    @Override
    public Vehicle findByName(String name) {
        return vehicles.stream()
                .filter(w -> w.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
