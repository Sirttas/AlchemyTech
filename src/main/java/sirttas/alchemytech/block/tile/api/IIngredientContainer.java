package sirttas.alchemytech.block.tile.api;

import sirttas.alchemytech.ingredient.Ingredient;

public interface IIngredientContainer extends IIngredientReceiver {

	public void clear();

	public Ingredient getIngredient(int index);
}
