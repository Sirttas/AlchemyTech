package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;

public class FireEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "fire";

	public FireEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strenth) {
		entity.setFire(10);
	}
}
