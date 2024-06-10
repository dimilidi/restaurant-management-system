package ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.UnitTesting.src.test.java.carShop;

import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.UnitTesting.src.main.java.carShop.Car;
import ExamPreparation.VehicleShop_JavaOOPRetakeExam18April2023.UnitTesting.src.main.java.carShop.CarShop;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CarShopTests {

    @Test(expected = NullPointerException.class)
    public void addNullCarThrows() {
        CarShop carShop = new CarShop();
        carShop.add(null);
    }

    @Test
    public void addCarSuccessfully() {
        CarShop carShop = new CarShop();
        Car car = new Car("Opel Astra", 100, 50.00);
        carShop.add(car);
        assertEquals("Opel Astra", car.getModel());
        assertEquals(100, car.getHorsePower());
        assertEquals(50.00, car.getPrice(), 0.01);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getCarsMutateCarCollectionThrows() {
        CarShop carShop = new CarShop();
        carShop.add(new Car("Opel Astra", 100, 50.00));
        carShop.getCars().remove(0);
    }

    @Test
    public void testFindAllCarsWithMaxHorsePowerByCarsWithUniqueHorsePower() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 120, 50.00);
        Car car2 = new Car("Opel Corsa", 60, 50.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);
        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);
        List<Car> result = carShop.findAllCarsWithMaxHorsePower(100);
        List<Car> carWithMaxHorsePower = List.of(car1);
        assertEquals(carWithMaxHorsePower, result);
    }

    @Test
    public void testFindAllCarsWithMaxHorsePowerByCarsWithSameHorsePower() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);

        carShop.add(car1);
        carShop.add(car3);

        List<Car> result = carShop.findAllCarsWithMaxHorsePower(100);

        assertEquals(new ArrayList<>(), result);
    }

    @Test
    public void testRemoveExistingCar() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);

        carShop.add(car1);
        carShop.add(car3);

        boolean removed = carShop.remove(car1);

        assertEquals(List.of(car3), carShop.getCars());
        assertTrue(removed);
    }

    @Test
    public void testRemoveNonExistingCar() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car2 = new Car("Opel Corsa", 60, 50.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);

        carShop.add(car1);
        carShop.add(car3);

        boolean removed = carShop.remove(car2);

        assertEquals(List.of(car1, car3), carShop.getCars());
        assertFalse(removed);
    }

    @Test
    public void testRemoveOnEmptyCarShop() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);

        boolean removed = carShop.remove(car1);

        assertFalse(removed);
    }

    @Test
    public void testGetTheMostLuxuryCarByCarsWithDifferentPrice() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 60.00);
        Car car2 = new Car("Opel Corsa", 60, 40.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);

        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);

        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();

        assertEquals(car1, theMostLuxuryCar);
    }

    @Test
    public void testGetTheMostLuxuryCarByCarsWithSamePrice() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car2 = new Car("Opel Corsa", 60, 50.00);
        Car car3 = new Car("Opel Zafira", 100, 50.00);

        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);

        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();

        assertEquals(car1, theMostLuxuryCar);
    }

    @Test
    public void testGetTheMostLuxuryCarWhenCarShopEmpty() {
        CarShop carShop = new CarShop();

        Car theMostLuxuryCar = carShop.getTheMostLuxuryCar();

        assertNull(theMostLuxuryCar);
    }

    @Test
    public void testFindAllCarByModelOnExistingModel() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car2 = new Car("Opel Astra", 80, 55.00);
        Car car3 = new Car("Opel Zafira", 100, 40.00);

        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);

        List<Car> result = carShop.findAllCarByModel("Opel Astra");
        assertEquals(List.of(car1, car2), result);
    }

    @Test
    public void testFindAllCarByModelOnNonExistingModel() {
        CarShop carShop = new CarShop();
        Car car1 = new Car("Opel Astra", 100, 50.00);
        Car car2 = new Car("Opel Astra", 80, 55.00);
        Car car3 = new Car("Opel Zafira", 100, 40.00);

        carShop.add(car1);
        carShop.add(car2);
        carShop.add(car3);

        List<Car> result = carShop.findAllCarByModel("Opel Corsa");
        assertEquals(new ArrayList<>(), result);
    }

}

