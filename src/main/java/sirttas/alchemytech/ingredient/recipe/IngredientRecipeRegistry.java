package sirttas.alchemytech.ingredient.recipe;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.tileentity.TileEntity;
import sirttas.alchemytech.AlchemyTech;

public class IngredientRecipeRegistry {

	private static List<IIngredientRecipe<?>> registry = new ArrayList<IIngredientRecipe<?>>();

	@SuppressWarnings({ "unchecked", "unused" })
	public static <T extends TileEntity> IIngredientRecipe<T> lookupRecipe(T instrument) {
		for (final IIngredientRecipe<?> recipe : registry) {
			try {
				final IIngredientRecipe<T> castedReciipe = (IIngredientRecipe<T>) recipe;
				if (castedReciipe.isAvalable(instrument)) {
					return castedReciipe;
				}
			} catch (final ClassCastException e) {
				AlchemyTech.T.debug("Skiping recipe: " + recipe.getClass() + " for " + instrument.getClass());
			}
		}
		return null;
	}

	public static void register(IIngredientRecipe<?> recipe) {
		registry.add(recipe);
	}
}
