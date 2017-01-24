package sirttas.alchemytech.ingredient.item;

import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import sirttas.alchemytech.ingredient.Ingredient;
import sirttas.alchemytech.ingredient.api.IItemIngredient;
import sirttas.alchemytech.ingredient.api.IPostInitIngredient;

public class OreDictionaryItemIngredient extends Ingredient implements IPostInitIngredient, IItemIngredient {

	private ItemStack stack;
	private String item;

	public OreDictionaryItemIngredient() {
		this(null);
	}

	public OreDictionaryItemIngredient(String name) {
		super(name);
	}

	public OreDictionaryItemIngredient(String name, String item) {
		super(name);
		this.item = item;
	}

	@Override
	public boolean isProducedFromItem(ItemStack stack) {
		return this.stack != null ? this.stack.isItemEqual(stack) : false;
	}

	@Override
	public ItemStack getStack() {
		return stack != null ? stack.copy() : null;
	}

	@Override
	public void postInit() {
		List<ItemStack> stacks = OreDictionary.getOres(item);

		if (stacks != null && stacks.size() > 0) {
			this.stack = stacks.get(0);
		}

	}

}
