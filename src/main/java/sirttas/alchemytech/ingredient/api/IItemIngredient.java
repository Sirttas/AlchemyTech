package sirttas.alchemytech.ingredient.api;

import net.minecraft.item.ItemStack;

public interface IItemIngredient {
	
	public boolean producedFromItem(ItemStack stack);
}
