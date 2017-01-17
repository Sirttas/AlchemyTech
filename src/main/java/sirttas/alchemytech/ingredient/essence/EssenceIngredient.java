package sirttas.alchemytech.ingredient.essence;

import net.minecraft.entity.Entity;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IEssenceIngredient;

public class EssenceIngredient extends Ingredient implements IEssenceIngredient {

	public EssenceIngredient() {

	}

	public EssenceIngredient(String name) {
		super(name);
	}

	@Override
	public void applyOnEntity(Entity entity, int strenth) {
		entity.getEntityData().setInteger("GLOWING", 200);
	}

}
