package sirttas.alchemytech.ingredient.recipe.instrument;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import sirttas.alchemytech.block.instrument.mixer.TileMixer;
import sirttas.alchemytech.ingredient.item.ItemIngredient;
import sirttas.alchemytech.ingredient.recipe.IIngredientRecipe;
import sirttas.alchemytech.item.ATItems;
import sirttas.alchemytech.item.ItemPreparation;

public class MixerRecipe implements IIngredientRecipe<TileMixer> {

	@Override
	public void process(TileMixer mixer) {
		if (this.isAvalable(mixer)) {
			ItemStack result = mixer.getStackInSlot(4);
			if (result == null || result.getItem() instanceof ItemPotion) {
				result = new ItemStack(ATItems.preparation);
			}

			for (int i = 0; i < 4
					&& ATItems.preparation.getIngredientCount(result) < ItemPreparation.MAX_INGREDIENTS; i++) {
				ItemStack stack = mixer.getStackInSlot(i);
				if (isMixable(stack)) {
					Item item = stack.getItem();

					if (item instanceof ItemPreparation) {
						ItemPreparation preparation = (ItemPreparation) item;

						while (preparation.getIngredientCount(stack) > 0
								&& ATItems.preparation.getIngredientCount(result) < ItemPreparation.MAX_INGREDIENTS) {
							ATItems.preparation.addIngredient(result, preparation.removeIngredientAt(stack, 0));
						}
						if (preparation.getIngredientCount(stack) == 0) {
							mixer.setInventorySlotContents(i, new ItemStack(Items.GLASS_BOTTLE));
						}
					} else {
						ATItems.preparation.addIngredient(result, ItemIngredient.getIngredientFromStack(stack));
						mixer.setInventorySlotContents(i, null);
					}
				}
			}
			mixer.setInventorySlotContents(4, result);
		}
	}

	@Override
	public boolean isAvalable(TileMixer mixer) {
		int ingredientCount = 0;
		ItemStack output = mixer.getStackInSlot(4);

		if (output == null || !((output.getItem() instanceof ItemPotion
				&& PotionUtils.getPotionFromItem(output).equals(PotionTypes.WATER)
				|| output.getItem() instanceof ItemPreparation))) {
			return false;
		}
		for (int i = 0; i < 4; i++) {
			ItemStack stack = mixer.getStackInSlot(i);
			if (isMixable(stack)) {
				Item item = stack.getItem();

				if (item instanceof ItemPreparation) {
					ingredientCount += ((ItemPreparation) item).getIngredientCount(stack);
				} else {
					ingredientCount++;
				}
			}
		}
		return ingredientCount > 0;
	}

	public static boolean isMixable(ItemStack stack) {
		if (stack != null) {
			Item item = stack.getItem();

			return item instanceof ItemPreparation || ItemIngredient.isIngredient(stack);
		}
		return false;
	}
}
