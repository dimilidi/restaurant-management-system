package ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.UnitTesting.src.test.java.stuntClimb;


import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.UnitTesting.src.main.java.stuntClimb.Climbing;
import ExamPreparation.Climbers_JavaOOPRetakeExam19December2023.UnitTesting.src.main.java.stuntClimb.RockClimber;
import org.junit.Test;

import static junit.framework.TestCase.*;

public class ClimbingTests {

    @Test(expected=NullPointerException.class)
    public void testCreateClimbingExceptionWhenNullName() {
        new Climbing(null, 10);
    }

    @Test(expected=NullPointerException.class)
    public void testCreateClimbingExceptionWhenEmptyName() {
        new Climbing("     ", 10);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testCreateClimbingExceptionWhenNegativeCapacity() {
        new Climbing("name", -10);
    }

    @Test
    public void testCreateClimbingWithZeroCapacity() {
        new Climbing("name", 0);
    }

    @Test
    public void testCreateClimbingWithValidNameAndPositiveCapacity() {
        new Climbing("name", 10);
    }

    @Test
    public void testGetName() {
        Climbing validName = new Climbing("name", 10);

        assertEquals("name", validName.getName());
    }

    @Test
    public void testGetCount() {
        Climbing climbing = new Climbing("name", 10);
        RockClimber climber = new RockClimber("climber", 80);

        climbing.addRockClimber(climber);

        int validCount = climbing.getCount();

        assertEquals(1, validCount);
    }

    @Test
    public void testGetCapacity() {
        Climbing validName = new Climbing("name", 10);

        assertEquals(10, validName.getCapacity());
    }


    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddClimbersWithSameName() {
        Climbing climbing = new Climbing("valid", 10);
        RockClimber climber = new RockClimber("name", 10);

        climbing.addRockClimber(climber);
        climbing.addRockClimber(climber);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCannotAddClimbersWithFullCapacity() {
        Climbing climbing = new Climbing("valid", 2);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);

        climbing.addRockClimber(climber1);
        climbing.addRockClimber(climber2);
        climbing.addRockClimber(climber3);
    }

    @Test
    public void testAddClimberIncreasesCount() {
        Climbing climbing = new Climbing("valid", 10);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);

        climbing.addRockClimber(climber1);
        climbing.addRockClimber(climber2);
        climbing.addRockClimber(climber3);

        assertEquals(3, climbing.getCount());
    }

    @Test
    public void removeClimberReturnsFalseForMissingName() {
        Climbing climbing = new Climbing("valid", 10);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);

        climbing.addRockClimber(climber1);

        boolean result = climbing.removeRockClimber(climber2.getName());

        assertFalse(result);
        assertEquals(1, climbing.getCount());
    }

    @Test
    public void testRemoveClimberDecreasesCount() {
        Climbing climbing = new Climbing("valid", 10);
        RockClimber climber1 = new RockClimber("name1", 10);
        RockClimber climber2 = new RockClimber("name2", 10);
        RockClimber climber3 = new RockClimber("name3", 10);

        climbing.addRockClimber(climber1);
        climbing.addRockClimber(climber2);
        climbing.addRockClimber(climber3);

        boolean result = climbing.removeRockClimber(climber2.getName());

        assertTrue(result);
        assertEquals(2, climbing.getCount());
    }

    @Test
    public void testRemoveClimberOnEmptyExcavationWillNotThrow() {
        Climbing climbing = new Climbing("valid", 10);

        boolean result = climbing.removeRockClimber("name2");
        assertFalse(result);
    }
}
