package ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.UnitTesting.src.test.java.scubaDive;


import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.UnitTesting.src.main.java.scubaDive.DeepWaterDiver;
import ExamPreparation.HarpoonDiver_JavaOOPExam9December2023.UnitTesting.src.main.java.scubaDive.Diving;
import org.junit.Test;

import static org.junit.Assert.*;

public class DivingTests {

    @Test(expected=NullPointerException.class)
    public void testInitializeDivingNullNameThrow() {
        new Diving(null, 10);
    }

    @Test(expected=NullPointerException.class)
    public void testInitializeDivingEmptyNameThrow() {
        new Diving("     ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInitializeDivingNegativeCapacityThrow() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        Diving diving = new Diving("name", -1);
        assertNull(diving);
    }

    @Test
    public void testInitializeDivingZeroCapacity() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        Diving diving = new Diving("name", 0);
        assertEquals("name", diving.getName());
        assertEquals(0, diving.getCapacity());
    }

    @Test
    public void testInitializeDivingPositiveCapacity() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        Diving diving = new Diving("name", 1);
        assertEquals("name", diving.getName());
        assertEquals(1, diving.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverNoCapacityThrow() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        DeepWaterDiver diver2 = new DeepWaterDiver("Diver2", 40.0);
        Diving diving = new Diving("name", 1);

        diving.addDeepWaterDiver(diver1);
        diving.addDeepWaterDiver(diver2);
    }

    @Test
    public void testGetName() {
        Diving validName = new Diving("name", 10);

        assertEquals("name", validName.getName());
    }

    @Test
    public void testGetCapacity() {
        Diving validCapacity = new Diving("name", 10);

        assertEquals(10, validCapacity.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDeepWaterDiverExistingDiverThrow() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        Diving diving = new Diving("name", 4);

        diving.addDeepWaterDiver(diver1);
        diving.addDeepWaterDiver(diver1);
    }

    @Test
    public void testAddDeepWaterDiverNonExistingDiver() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        Diving diving = new Diving("name", 4);

        diving.addDeepWaterDiver(diver1);
        assertEquals(1, diving.getCount());
    }

    @Test
    public void testRemoveDeepWaterDiverExistingDiver() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        DeepWaterDiver diver2 = new DeepWaterDiver("Diver2", 40.0);
        Diving diving = new Diving("name", 4);

        diving.addDeepWaterDiver(diver1);
        diving.addDeepWaterDiver(diver2);

        boolean isRemoved = diving.removeDeepWaterDiver(diver1.getName());
        assertTrue(isRemoved);
        assertEquals(1, diving.getCount());
    }

    @Test
    public void testRemoveDeepWaterDiverNonExistingDiver() {
        DeepWaterDiver diver1 = new DeepWaterDiver("Diver1", 30.0);
        DeepWaterDiver diver2 = new DeepWaterDiver("Diver2", 40.0);
        DeepWaterDiver diver3 = new DeepWaterDiver("Diver3", 50.0);
        Diving diving = new Diving("name", 4);

        diving.addDeepWaterDiver(diver1);
        diving.addDeepWaterDiver(diver2);

        boolean isRemoved = diving.removeDeepWaterDiver(diver3.getName());
        assertFalse(isRemoved);
        assertEquals(2, diving.getCount());
    }
}
