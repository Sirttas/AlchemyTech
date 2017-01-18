package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;

public class EnergizedEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "energized";

	public EnergizedEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strenth) {
		// entity.getEntityData().setInteger("GLOWING", 200);
	}
}
