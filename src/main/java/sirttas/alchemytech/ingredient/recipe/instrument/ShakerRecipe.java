package sirttas.alchemytech.ingredient.recipe.instrument;

import sirttas.alchemytech.block.instrument.shaker.TileShaker;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.recipe.SingleSlotIngredientRecipe;

public class ShakerRecipe extends SingleSlotIngredientRecipe<TileShaker> {

	public ShakerRecipe(Ingredient output, Ingredient input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public ShakerRecipe(Ingredient output, Ingredient[] input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public ShakerRecipe(Ingredient[] output, Ingredient input) {
		super(output, input);
		clazz = TileShaker.class;
	}

	public ShakerRecipe(Ingredient[] output, Ingredient[] input) {
		super(output, input);
		clazz = TileShaker.class;
	}
}
