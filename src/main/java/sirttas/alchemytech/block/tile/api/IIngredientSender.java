package sirttas.alchemytech.block.tile.api;

import sirttas.alchemytech.ingredient.Ingredient;

public interface IIngredientSender extends IInstrumentTile {

	public Ingredient removeIngredient(int index);

	public boolean canExtract(int index);

}
