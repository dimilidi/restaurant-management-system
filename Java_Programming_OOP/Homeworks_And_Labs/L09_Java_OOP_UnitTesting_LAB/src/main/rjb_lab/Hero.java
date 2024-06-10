package Homeworks_And_Labs.L09_Java_OOP_UnitTesting_LAB.src.main.rjb_lab;

public class Hero {

    private String name;
    private int experience;
    private Weapon weapon;

    public Hero(String name, Weapon weapon) {
        this.name = name;
        this.experience = 0;
        this.weapon = weapon;
    }

    public String getName() {
        return this.name;
    }

    public int getExperience() {
        return this.experience;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public void attack(Target target) {
        this.weapon.attack(target);

        if (target.isDead()) {
            this.experience += target.giveExperience();
        }
    }
}
