package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsNewFactory.models.units;

public class Gunner extends AbstractUnit{
    private static final int GUNNER_HEALTH = 20;
    private static final int GUNNER_DAMAGE = 20;
    public Gunner() {
        super(GUNNER_HEALTH, GUNNER_DAMAGE);
}
}
