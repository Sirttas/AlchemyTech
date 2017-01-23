package sirttas.alchemytech.ingredient;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;

public class OreDictionaryItemIngredient extends Ingredient implements IPostInitIngredient, IItemIngredient {

	@Override
	public boolean isProducedFromItem(ItemStack stack) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ItemStack getStack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

}
