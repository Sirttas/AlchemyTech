package sirttas.alchemytech.block.tile.api;

import sirttas.alchemytech.ingredient.Ingredient;

public interface IIngredientReceiver {

	public void addIngredient(Ingredient ingredient);

	public boolean canReceive(Ingredient ingredient);
}
