package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sirttas.alchemytech.block.instrument.extractor.TileExtractor;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.item.ItemPreparation;

public class ExtractorRecipe implements IIngredientRecipe<TileExtractor> {

	@Override
	public void process(TileExtractor instrument) {
		ItemStack stack = instrument.getStackInSlot(0);
		World world = instrument.getWorld();
		BlockPos pos = instrument.getPos();

		if (stack != null && stack.getItem() instanceof ItemPreparation) {
			ItemPreparation preparation = (ItemPreparation) stack.getItem();
			Ingredient[] ingredients = preparation.getIngredients(stack);

			for (Ingredient ingredient : ingredients) {
				if (ingredient instanceof IItemIngredient) {
					ItemStack popup = ((IItemIngredient) ingredient).getStack();

					if (popup != null) {
						EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
								popup);

						world.spawnEntityInWorld(item);
					}
				}
			}
			if (instrument.getStackInSlot(0) != null) {
				instrument.setInventorySlotContents(0, new ItemStack(Items.GLASS_BOTTLE));
			}
		}
		if (instrument.canExtract(0)) {
			Ingredient ingredient = instrument.removeIngredient(0);

			if (ingredient instanceof IItemIngredient) {
				ItemStack popup = ((IItemIngredient) ingredient).getStack();

				if (popup != null) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
							popup);

					world.spawnEntityInWorld(item);
				}
			}
		}

	}

	@Override
	public boolean isAvalable(TileExtractor instrument) {
		ItemStack stack = instrument.getStackInSlot(0);

		if (stack != null && stack.getItem() instanceof ItemPreparation) {
			return true;
		} else if (instrument.canExtract(0)) {
			return true;
		}
		return false;
	}

}
