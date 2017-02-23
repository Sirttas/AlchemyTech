package sirttas.alchemytech.ingredient.recipe.instrument;

import sirttas.alchemytech.block.instrument.centrifuge.TileCentrifuge;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.recipe.CraftingInstrumentRecipe;

public class CentrifugeRecipe extends CraftingInstrumentRecipe<TileCentrifuge> {

	public CentrifugeRecipe(Ingredient output, Ingredient input) {
		super(output, input);
		clazz = TileCentrifuge.class;
	}

	public CentrifugeRecipe(Ingredient output, Ingredient[] input) {
		super(output, input);
		clazz = TileCentrifuge.class;
	}

	public CentrifugeRecipe(Ingredient[] output, Ingredient input) {
		super(output, input);
		clazz = TileCentrifuge.class;
	}

	public CentrifugeRecipe(Ingredient[] output, Ingredient[] input) {
		super(output, input);
		clazz = TileCentrifuge.class;
	}
}
