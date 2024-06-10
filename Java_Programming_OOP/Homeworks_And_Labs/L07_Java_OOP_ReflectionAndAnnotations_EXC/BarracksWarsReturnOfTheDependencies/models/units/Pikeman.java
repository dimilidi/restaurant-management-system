package Homeworks_And_Labs.L07_Java_OOP_ReflectionAndAnnotations_EXC.BarracksWarsReturnOfTheDependencies.models.units;

public class Pikeman extends AbstractUnit {

	private static final int PIKEMAN_HEALTH = 30;
	private static final int PIKEMAN_DAMAGE = 15;

	public Pikeman() {
		super(PIKEMAN_HEALTH, PIKEMAN_DAMAGE);
	}
}
