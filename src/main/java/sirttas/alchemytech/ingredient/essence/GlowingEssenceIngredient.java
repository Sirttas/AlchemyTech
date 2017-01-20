package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;

public class GlowingEssenceIngredient extends EssenceIngredient {

	private static final String NAME = "glowing";

	public GlowingEssenceIngredient() {
		super(NAME);
	}

	@Override
	public void applyOnEntity(Entity entity, int strength) {
		// entity.getEntityData().setInteger("GLOWING", 1200);
	}
}
