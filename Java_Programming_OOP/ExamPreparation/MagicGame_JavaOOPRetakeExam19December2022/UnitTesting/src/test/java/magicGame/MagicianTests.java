package ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.UnitTesting.src.test.java.magicGame;

import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.UnitTesting.src.main.java.magicGame.Magic;
import ExamPreparation.MagicGame_JavaOOPRetakeExam19December2022.UnitTesting.src.main.java.magicGame.Magician;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class MagicianTests {
    @Test
    public void testConstructor() {
        Magician magician = new Magician("username", 42);

        assertEquals("username", magician.getUsername());
        assertEquals(42, magician.getHealth());
    }

    @Test//(expected = NullPointerException.class)
    public void testConstructorThrowsOnNullUsername() {
        assertThrows(NullPointerException.class, () ->  new Magician(null, 42));
    }

    @Test//(expected = NullPointerException.class)
    public void testConstructorThrowsOnEmptyUsername() {
        assertThrows(NullPointerException.class, () ->  new Magician("", 42));
    }

    @Test//(expected = NullPointerException.class)
    public void testConstructorThrowsOnWhitespaceUsername() {
        assertThrows(NullPointerException.class, () ->  new Magician("   ", 42));
    }

    @Test//(expected = IllegalArgumentException.class)
    public void testConstructorThrowsOnNegativeHealth() {
        assertThrows(IllegalArgumentException.class, "Health cannot be bellow zero!", () ->  new Magician("username", -1));
    }

    @Test
    public void testAddMagic() {
        initializeMagicianWith1MagicAndAssertGet();
    }

    @Test
    public void testAddNullMagicThrows() {
        Magician magician = new Magician("username", 42);
        assertThrows(NullPointerException.class,  () ->  magician.addMagic(null));
    }

    @Test
    public void testAddMagicTwice() {
        Magician magician = new Magician("username", 42);
        magician.addMagic(new Magic("magicA", 13));
        magician.addMagic(new Magic("magicB", 5));

        Magic magicA = magician.getMagic("magicA");
        assertMagic("magicA", 13, magicA);

        Magic magicB = magician.getMagic("magicB");
        assertMagic("magicB", 5, magicB);
    }

    @Test
    public void testGetMagic() {
        initializeMagicianWith1MagicAndAssertGet();
    }

    @Test
    public void testGetMagicEmptyListNotFound() {
        Magician magician = new Magician("username", 42);

        assertNull(magician.getMagic("not-existing"));
    }

    @Test
    public void testGetMagicNotFound() {
        Magician magician = new Magician("username", 42);
        magician.addMagic(new Magic("magicA", 13));

        assertNull(magician.getMagic("not-existing"));
    }

    @Test
    public void testGetMagics() {
        Magician magician = new Magician("username", 42);

        magician.addMagic(new Magic("magicA", 13));
        magician.addMagic(new Magic("magicB", 5));

        List<Magic> magics = magician.getMagics();
        assertEquals(2, magics.size());
        assertMagic("magicA", 13, magics.get(0));
        assertMagic("magicB", 5, magics.get(1));
    }

    @Test
    public void testGetMagicsSingle() {
        Magician magician = new Magician("username", 42);

        magician.addMagic(new Magic("magicA", 13));

        List<Magic> magics = magician.getMagics();
        assertEquals(1, magics.size());
        assertMagic("magicA", 13, magics.get(0));
    }

    @Test
    public void testGetMagicsNone() {
        Magician magician = new Magician("username", 42);

        List<Magic> magics = magician.getMagics();
        assertEquals(0, magics.size());
    }
    @Test
    public void testGetHealth() {
        Magician magician = new Magician("username", 42);
        assertEquals(42, magician.getHealth());
    }

    @Test
    public void testRemoveMagic() {
        Magician magician = new Magician("username", 42);

        magician.addMagic(new Magic("magicA", 13));
        Magic toRemove = new Magic("magicB", 5);
        magician.addMagic(toRemove);

        assertTrue(magician.removeMagic(toRemove));
        assertEquals(1, magician.getMagics().size());
        assertNull(magician.getMagic(toRemove.getName()));
    }

    @Test
    public void testRemoveMagicRemovesLastRemaining() {
        Magician magician = new Magician("username", 42);

        Magic toRemove = new Magic("magicB", 5);
        magician.addMagic(toRemove);

        assertTrue(magician.removeMagic(toRemove));
        assertEquals(0, magician.getMagics().size());
        assertNull(magician.getMagic(toRemove.getName()));
    }

    @Test
    public void testRemoveMagicTwice() {
        Magician magician = new Magician("username", 42);

        magician.addMagic(new Magic("magicA", 13));
        Magic toRemoveFirst = new Magic("magicB", 5);
        Magic toRemoveNext = new Magic("magicC", 25);
        magician.addMagic(toRemoveFirst);
        magician.addMagic(toRemoveNext);

        assertTrue(magician.removeMagic(toRemoveFirst));
        assertTrue(magician.removeMagic(toRemoveNext));

        assertEquals(1, magician.getMagics().size());
        assertNull(magician.getMagic(toRemoveFirst.getName()));
        assertNull(magician.getMagic(toRemoveNext.getName()));
    }

    @Test
    public void testRemoveMagicNotFound() {
        Magician magician = new Magician("username", 42);

        magician.addMagic(new Magic("magicA", 13));
        magician.addMagic(new Magic("magicB", 5));

        assertFalse(magician.removeMagic(new Magic("non-existing", 100)));
        assertEquals(2, magician.getMagics().size());
    }

    @Test
    public void testGetUsername() {
        Magician magician = new Magician("username", 42);
        assertEquals("username", magician.getUsername());
    }

    @Test
    public void testTakeDamageHealthLargerThanDamage() {
        Magician magician = new Magician("username", 42);

        magician.takeDamage(13);
        assertEquals(29, magician.getHealth());

    }

    @Test
    public void testTakeDamageZeroHealthThrows() {
        Magician magician = new Magician("username", 0);

        assertThrows(IllegalStateException.class, () ->  magician.takeDamage(13));

    }

    @Test
    public void testTakeDamageHealthLessThanDamageSetsHealthZero() {
        Magician magician = new Magician("username", 13);

        magician.takeDamage(14);
        assertEquals(0, magician.getHealth());
    }

    @Test
    public void testTakeDamageHealthEqualToDamageSetsHealthZero() {
        Magician magician = new Magician("username", 13);

        magician.takeDamage(13);
        assertEquals(0, magician.getHealth());
    }

    @Test
    public void testTakeZeroDamageDoesNotChangeHealth() {
        Magician magician = new Magician("username", 13);

        magician.takeDamage(0);
        assertEquals(13, magician.getHealth());
    }

    private static void initializeMagicianWith1MagicAndAssertGet() {
        Magician magician = new Magician("username", 42);
        magician.addMagic(new Magic("magic", 13));

        Magic actualMagic = magician.getMagic("magic");

        assertMagic("magic", 13, actualMagic);
    }

    private static void assertMagic(String expectedName, int expectedBullets, Magic actualMagic) {
        assertNotNull(actualMagic);
        assertEquals(expectedName, actualMagic.getName());
        assertEquals(expectedBullets, actualMagic.getBullets());
    }


    private static <T extends Throwable> void assertThrows(Class<T> exceptionType, Runnable runnable) {
        assertThrows(exceptionType, null, runnable);
    }

    private static <T extends Throwable> void assertThrows(Class<T> exceptionType, String exceptionMessage, Runnable runnable) {
        try {
            runnable.run();
            fail("Expected " + exceptionType.getClass().getSimpleName());
        } catch (Throwable throwable) {
            assertEquals(exceptionType, throwable.getClass());

            if (exceptionMessage != null){
                assertEquals(exceptionMessage, throwable.getMessage());
            }

        }
    }

}
