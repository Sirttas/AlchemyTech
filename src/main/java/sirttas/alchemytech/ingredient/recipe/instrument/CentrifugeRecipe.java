package sirttas.alchemytech.ingredient.recipe.instrument;

import sirttas.alchemytech.block.instrument.shaker.TileShaker;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.recipe.SingleSlotIngredientRecipe;

public class CentrifugeRecipe extends SingleSlotIngredientRecipe<TileShaker> {

	public CentrifugeRecipe(Ingredient output, Ingredient input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public CentrifugeRecipe(Ingredient output, Ingredient[] input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public CentrifugeRecipe(Ingredient[] output, Ingredient input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public CentrifugeRecipe(Ingredient[] output, Ingredient[] input) {
		super(output, input);
		clazz = TileShaker.class;
	}
}
