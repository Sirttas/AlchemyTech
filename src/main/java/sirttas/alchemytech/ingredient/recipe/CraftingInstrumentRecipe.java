package sirttas.alchemytech.ingredient.recipe;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.block.tile.instrument.TileInstrument;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.item.ItemPreparation;

public abstract class CraftingInstrumentRecipe<T extends TileInstrument> implements IIngredientRecipe<T> {

	protected Ingredient[] output;
	protected Ingredient[] input;
	protected Class<T> clazz;

	public CraftingInstrumentRecipe(Ingredient output, Ingredient input) {
		this(output, new Ingredient[] { input });
	}

	public CraftingInstrumentRecipe(Ingredient output, Ingredient[] input) {
		this(new Ingredient[] { output }, input);
	}

	public CraftingInstrumentRecipe(Ingredient[] output, Ingredient input) {
		this(output, new Ingredient[] { input });
	}

	public CraftingInstrumentRecipe(Ingredient[] output, Ingredient[] input) {
		this.output = output;
		this.input = input;
	}

	@Override
	public void process(T instrument) {
		ItemStack stack = instrument.getStackInSlot(0);
		ItemPreparation preparation = (ItemPreparation) stack.getItem();

		if (isAvalable(instrument)) {
			for (Ingredient ingredient : input) {
				preparation.removeIngredient(stack, ingredient);
			}
			for (Ingredient ingredient : output) {
				preparation.addIngredient(stack, ingredient);
			}
		}
	}

	@Override
	public boolean isAvalable(T instrument) {
		if (instrument == null || !clazz.isAssignableFrom(instrument.getClass())) {
			return false;
		}
		ItemStack stack = instrument.getStackInSlot(0);

		return stack != null && stack.getItem() instanceof ItemPreparation
				&& ((ItemPreparation) stack.getItem()).hasAllIngredient(stack, input);
	}
}
