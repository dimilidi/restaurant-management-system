package ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.UnitTesting.src.test.java.toyStore;

import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.UnitTesting.src.main.java.toyStore.Toy;
import ExamPreparation.ChristmasPastryShop_JavaOOPExam10December2022.UnitTesting.src.main.java.toyStore.ToyStore;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ToyStoryTest {
    private ToyStore toyStore;
    private Toy toy1;
    private Toy toy2;

    @Before
    public void setUp() {
        toyStore = new ToyStore();
        toy1 = new Toy("Manufacturer1", "ToyId1");
        toy2 = new Toy("Manufacturer2", "ToyId2");
    }

    @Test
    public void testAddToyToEmptyShelf() throws OperationNotSupportedException {
        String shelf = "A";
        String result = toyStore.addToy(shelf, toy1);
        assertEquals("Toy:ToyId1 placed successfully!", result);
        assertEquals(toy1, toyStore.getToyShelf().get(shelf));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyToNonexistentShelf() throws OperationNotSupportedException {
        toyStore.addToy("X", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyToOccupiedShelf() throws OperationNotSupportedException {
        String shelf = "A";
        toyStore.addToy(shelf, toy1);
        toyStore.addToy(shelf, toy2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddDuplicateToy() throws OperationNotSupportedException {
        String shelf1 = "A";
        String shelf2 = "B";
        toyStore.addToy(shelf1, toy1);
        toyStore.addToy(shelf2, toy1);
    }

    @Test
    public void testRemoveToy() throws OperationNotSupportedException {
        String shelf = "A";
        toyStore.addToy(shelf, toy1);
        String result = toyStore.removeToy(shelf, toy1);
        assertEquals("Remove toy:ToyId1 successfully!", result);
        assertNull(toyStore.getToyShelf().get(shelf));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyFromNonexistentShelf() {
        toyStore.removeToy("X", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentToy() throws OperationNotSupportedException {
        String shelf = "A";
        toyStore.addToy(shelf, toy1);
        toyStore.removeToy(shelf, toy2);
    }
}