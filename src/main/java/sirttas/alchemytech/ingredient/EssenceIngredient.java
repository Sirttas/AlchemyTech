package sirttas.alchemytech.ingredient;

import net.minecraft.entity.Entity;
import sirttas.alchemytech.ingredient.api.IEssenceIngredient;

public class EssenceIngredient extends Ingredient implements IEssenceIngredient {

	public EssenceIngredient() {

	}

	public EssenceIngredient(String name) {
		super(name);
	}

	@Override
	public void applyOnEntity(Entity eneity, int strenth) {
		// TODO Auto-generated method stub

	}

}
