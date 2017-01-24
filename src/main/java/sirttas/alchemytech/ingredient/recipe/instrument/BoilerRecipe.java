package sirttas.alchemytech.ingredient.recipe.instrument;

import sirttas.alchemytech.block.instrument.boiler.TileBoiler;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.recipe.SingleSlotIngredientRecipe;

public class BoilerRecipe extends SingleSlotIngredientRecipe<TileBoiler> {

	public BoilerRecipe(Ingredient output, Ingredient input) {
		super(output, input);
		clazz = TileBoiler.class;
	}

	public BoilerRecipe(Ingredient output, Ingredient[] input) {
		super(output, input);
		clazz = TileBoiler.class;
	}

	public BoilerRecipe(Ingredient[] output, Ingredient input) {
		super(output, input);
		clazz = TileBoiler.class;
	}

	public BoilerRecipe(Ingredient[] output, Ingredient[] input) {
		super(output, input);
		clazz = TileBoiler.class;
	}
}
