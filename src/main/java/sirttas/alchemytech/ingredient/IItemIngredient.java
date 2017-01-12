package sirttas.alchemytech.ingredient;

import net.minecraft.item.ItemStack;

public interface IItemIngredient {
	
	public boolean producedFromItem(ItemStack stack);
}
