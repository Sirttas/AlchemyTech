package sirttas.alchemytech.ingredient.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import sirttas.alchemytech.AlchemyTech;

public class IngredientRecipeRegistry {

	private static List<IIngredientRecipe> registry = new ArrayList<IIngredientRecipe>();

	public static void register(IIngredientRecipe recipe) {
		registry.add(recipe);
	}

	@SuppressWarnings("unchecked")
	public static <T extends TileEntity> IIngredientRecipe<T> lookupRecipe(T instrument) {
		for (IIngredientRecipe recipe : registry) {
			try {
				if (recipe.isAvalable(instrument)) {
					return recipe;
				}
			} catch (ClassCastException e) {
				AlchemyTech.T.debug("Skiping recipe: " + recipe.getClass() + " for " + instrument.getClass());
			}
		}
		return null;
	}
}
