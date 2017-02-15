package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.block.instrument.dissolver.TileDissolver;
import sirttas.alchemytech.ingredient.item.ItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;

public class DissolverRecipe implements IIngredientRecipe<TileDissolver> {

	@Override
	public void process(TileDissolver dissolver) {
		if (this.isAvalable(dissolver)) {
			dissolver.addIngredient(ItemIngredient.getIngredientFromStack(dissolver.getStackInSlot(0)));
		}
	}

	@Override
	public boolean isAvalable(TileDissolver dissolver) {
		ItemStack stack = dissolver.getStackInSlot(0);

		return (stack != null && dissolver.getIngredient(0) == null && MixerRecipe.isMixable(stack));
	}
}
