package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sirttas.alchemytech.block.instrument.extractor.TileExtractor;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.ItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.item.ItemPreparation;

public class FilterRecipe implements IIngredientRecipe<TileExtractor> {

	@Override
	public void process(TileExtractor instrument) {
		ItemStack stack = instrument.getStackInSlot(0);
		if (stack != null && stack.getItem() instanceof ItemPreparation) {
			ItemPreparation preparation = (ItemPreparation) stack.getItem();
			Ingredient[] ingredients = preparation.getIngredients(stack);
			World world = instrument.getWorld();
			BlockPos pos = instrument.getPos();

			for (Ingredient ingredient : ingredients) {
				if (ingredient instanceof ItemIngredient) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
							((ItemIngredient) ingredient).getStack());

					world.spawnEntityInWorld(item);
				}
			}
			instrument.setInventorySlotContents(0, new ItemStack(Items.GLASS_BOTTLE));
		}

	}

	@Override
	public boolean isAvalable(TileExtractor instrument) {
		ItemStack stack = instrument.getStackInSlot(0);
		if (stack != null && stack.getItem() instanceof ItemPreparation) {
			return true;
		}
		return false;
	}

}
