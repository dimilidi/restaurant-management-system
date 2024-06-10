/*
package Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.test.rjb_lab;

import Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab.Hero;
import Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab.Target;
import Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab.Weapon;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

public class HeroTest {

    private static final int EXPERIENCE = 200;

    @Test
    public void test_Hero_Gets_Experience_When_Target_Dies() {
        Weapon weapon = mock(Weapon.class);
        Target target = mock(Target.class);

        when(target.isDead()).thenReturn(true);
        when(target.giveExperience()).thenReturn(EXPERIENCE);

        Hero hero = new Hero("Pointer", weapon);
        hero.attack(target);

        assertEquals(EXPERIENCE, hero.getExperience());
    }

}
*/
