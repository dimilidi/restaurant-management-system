package ExamPreparation.RobotService_JavaOOPRegularExam8April2023.UnitTesting.src.test.java.robots;

import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.UnitTesting.src.main.java.robots.Robot;
import ExamPreparation.RobotService_JavaOOPRegularExam8April2023.UnitTesting.src.main.java.robots.Service;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ServiceTests {


    @Test(expected = IllegalArgumentException.class)
    public void testCreateServiceWithInvalidCapacityThrows() {
        new Service("Service1", -25);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateServiceWithEmptyNameThrows() {
        new Service("", 25);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateServiceWithNameNullThrows() {
        new Service(null, 25);
    }

    @Test
    public void testCreateCorrectServiceObject() {
        Service service = new Service("Service1", 25);

        assertEquals("Service1", service.getName());
        assertEquals(25, service.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddRobotToServiceWhenNoCapacityThrows() {
        Service service = new Service("Service1", 0);
        Robot robot = new Robot("R2D2");
        service.add(robot);
    }

    @Test
    public void testAddRobotToServiceWhenEnoughCapacity() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);

        assertEquals(1, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingRobotThrows() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.remove(robot.getName());
    }

    @Test
    public void testRemoveExistingRobotDecreasesCount() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);

        service.remove("R2D2");

        assertEquals(0, service.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForSaleNonExistingRobotThrows() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.forSale(robot.getName());
    }

    @Test
    public void testForSaleSetsRobotSetReadyForSaleToFalse() {
        Service service = new Service("Service1", 5);
        Robot robot = new Robot("R2D2");
        service.add(robot);
        Robot actualRobot = service.forSale(robot.getName());

        assertFalse(actualRobot.isReadyForSale());
    }

    @Test
    public void testReport() {
        Service service = new Service("Service1", 5);
        Robot robot1 = new Robot("R2D2");
        Robot robot2 = new Robot("ROBOT2");
        Robot robot3 = new Robot("ROBOT3");
        service.add(robot1);
        service.add(robot2);
        service.add(robot3);

        String actualReport = service.report();

        assertEquals("The robot R2D2, ROBOT2, ROBOT3 is in the service Service1!", actualReport);
    }
}
