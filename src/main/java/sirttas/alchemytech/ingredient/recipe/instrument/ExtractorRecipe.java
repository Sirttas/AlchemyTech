package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sirttas.alchemytech.block.instrument.extractor.TileExtractor;
import sirttas.alchemytech.block.instrument.extractor.top.TileExtractorTop;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.item.ItemPreparation;

public class ExtractorRecipe implements IIngredientRecipe<TileExtractor> {

	private void createItem(Ingredient ingredient, World world, BlockPos pos) {
		if (ingredient instanceof IItemIngredient) {
			ItemStack stack = ((IItemIngredient) ingredient).getStack();

			if (stack != null) {
				TileEntity te = world.getTileEntity(pos.add(0, 1, 0));
				TileExtractorTop top = null;

				if (te != null && te instanceof TileExtractorTop) {
					top = (TileExtractorTop) te;
				}

				if (top != null) {
					stack = top.tryInsertItem(stack);
				}
				if (stack != null) {
					EntityItem item = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 1.1, pos.getZ() + 0.5,
							stack);

					world.spawnEntityInWorld(item);
				}
			}
		}
	}

	@Override
	public void process(TileExtractor instrument) {
		ItemStack stack = instrument.getStackInSlot(0);
		World world = instrument.getWorld();
		BlockPos pos = instrument.getPos();

		if (stack != null && stack.getItem() instanceof ItemPreparation) {
			ItemPreparation preparation = (ItemPreparation) stack.getItem();
			Ingredient[] ingredients = preparation.getIngredients(stack);

			for (Ingredient ingredient : ingredients) {
				createItem(ingredient, world, pos);
			}
			if (instrument.getStackInSlot(0) != null) {
				instrument.setInventorySlotContents(0, new ItemStack(Items.GLASS_BOTTLE));
			}
		}
		if (instrument.canExtract(0)) {
			Ingredient ingredient = instrument.removeIngredient(0);

			createItem(ingredient, world, pos);
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
