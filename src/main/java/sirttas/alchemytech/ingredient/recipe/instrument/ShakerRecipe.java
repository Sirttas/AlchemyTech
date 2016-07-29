package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.block.instrument.shaker.TileShaker;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.item.ItemPreparation;

public class ShakerRecipe implements IIngredientRecipe<TileShaker> {

	Ingredient[] output;
	Ingredient[] input;

	public ShakerRecipe(Ingredient output, Ingredient input) {
		this(output, new Ingredient[] { input });
	}

	public ShakerRecipe(Ingredient output, Ingredient[] input) {
		this(new Ingredient[] { output }, input);
	}

	public ShakerRecipe(Ingredient[] output, Ingredient[] input) {
		this.output = output;
		this.input = input;
	}


	@Override
	public void process(TileShaker shaker) {
		ItemStack stack = shaker.getStackInSlot(0);
		ItemPreparation preparation = (ItemPreparation) stack.getItem();

		if (isAvalable(shaker)) {
			for (Ingredient ingredient : input) {
				preparation.removeIngredient(stack, ingredient);
			}
			for (Ingredient ingredient : output) {
				preparation.addIngredient(stack, ingredient);
			}

		}

	}

	@Override
	public boolean isAvalable(TileShaker shaker) {
		ItemStack stack = shaker.getStackInSlot(0);

		return stack != null && stack.getItem() instanceof ItemPreparation
				&& ((ItemPreparation) stack.getItem()).hasAllIngredient(stack, input);
	}

}
