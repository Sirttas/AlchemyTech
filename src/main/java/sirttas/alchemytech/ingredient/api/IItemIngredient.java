package sirttas.alchemytech.ingredient.api;

import net.minecraft.item.ItemStack;

public interface IItemIngredient {
	
	public boolean isProducedFromItem(ItemStack stack);

	ItemStack getStack();
}
