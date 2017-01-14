package sirttas.alchemytech.ingredient;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import sirttas.alchemytech.AlchemyTech;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;

public class MultipleItemIngredient extends Ingredient implements IPostInitIngredient, IItemIngredient {

	private List<ItemStack> stacks = new ArrayList<ItemStack>();

	public MultipleItemIngredient() {
		this(null);
	}

	public MultipleItemIngredient(String name) {
		super(name);
	}

	public MultipleItemIngredient(String name, ItemStack[] stacks) {
		super(name);
		for (ItemStack stack : stacks) {
			this.stacks.add(stack);
		}
	}
	
	@Override
	public void postInit() {
		ItemIngredient.itemToIngredient.add(this);
		if (stacks.isEmpty()) {
			AlchemyTech.T.warn("ItemStacks for MultipleItemIngredient: '" + this.getRegistryName()
					+ "' not set. It may cause a potential game crash.");
		}
	}

	public MultipleItemIngredient addStack(ItemStack stack) {
		this.stacks.add(stack.copy());
		return this;
	}

	@Override
	public boolean producedFromItem(ItemStack stack) {
		for (ItemStack item : stacks) {
			if (item.isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}

}